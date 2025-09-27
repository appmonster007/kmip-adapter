package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
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
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "AttributeValue 'value' must be a non-empty array");
            return null;
        }

        Object obj;
        switch (encodingType) {
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
        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("AttributeValue is not supported for KMIP spec %s", spec));
        }

        return attributeValue;
    }
}
