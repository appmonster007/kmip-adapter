package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AttributeValueJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue> {
    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValue.class, "JSON node cannot be null for AttributeValue deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.class, "Invalid KMIP tag for AttributeValue");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.class, String.format("Failed to parse KMIP tag for AttributeValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValue.class,
                    String.format("Expected object with %s tag for AttributeValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
        ) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing or non-text 'type' field for AttributeValue");
            return null;
        }
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).get();

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(AttributeValue.class, "AttributeValue 'value' must be a non-empty");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        Object obj;
        switch (encodingType) {
            case STRUCTURE -> {
                List<KmipDataType> values = new ArrayList<>();
                for (JsonNode childNode : valueNode) {
                    values.add(deserializeObjects(childNode, p, ctxt));
                }
                obj = values;
            }
            case INTEGER, ENUMERATION, INTERVAL -> obj = valueNode.intValue();
            case BOOLEAN -> obj = valueNode.asBoolean();
            case DATE_TIME -> obj = OffsetDateTime.parse(valueNode.asText());
            case LONG_INTEGER -> obj = valueNode.longValue();
            case TEXT_STRING -> obj = valueNode.asText();
            case BYTE_STRING -> obj = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
            case BIG_INTEGER -> obj = p.getCodec().treeToValue(valueNode, BigInteger.class);
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }
        AttributeValue attributeValue = AttributeValue.builder().encodingType(encodingType).value(obj).build();

        // Validate KMIP spec compatibility
        if (!attributeValue.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("AttributeValue is not supported for KMIP spec %s", spec));
        }

        return attributeValue;
    }

    private KmipDataType deserializeObjects(JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!node.has("tag") || !node.has("type") || !node.has("value")) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing 'tag', 'type', or 'value' field in JSON");
            return null;
        }

        if (!node.has("tag") && !node.get("tag").isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid 'tag' field in JSON");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.class, "Invalid KMIP tag for AttributeValue");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.class, String.format("Failed to parse KMIP tag for AttributeValue: %s", e.getMessage()));
            return null;
        }

        if (!node.has("type") && !node.get("type").isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid 'type' field in JSON");
            return null;
        }
        String type = node.get("type").asText();
        EncodingType encodingType = EncodingType.fromName(type).get();


        KmipSpec spec = KmipContext.getSpec();
        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(spec, tag.getValue(), encodingType);

        return p.getCodec().treeToValue(node, clazz);
    }
}
