package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttributeValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue> {
    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Expected XML object for AttributeValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid Tag for AttributeValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing or invalid '@type' attribute for AttributeValue");
            return null;
        }
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).get();

        KmipSpec spec = KmipContext.getSpec();
        Object obj;
        if (encodingType == EncodingType.STRUCTURE) {
            List<KmipDataType> values = new ArrayList<>();

            // Process all fields in the XML
            var fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                if (entry.getValue().isObject()) {
                    values.add(deserializeObjects(entry.getKey(), entry.getValue(), p, ctxt));
                }
            }
            obj = values;
        } else {
            JsonNode valueNode = node.get("value");
            if (valueNode == null) {
                ctxt.reportInputMismatch(AttributeValue.class,
                        "Missing 'value' for AttributeValue");
                return null;
            }

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
        }


        AttributeValue attributeValue = AttributeValue.builder().encodingType(encodingType).value(obj).build();

        if (!attributeValue.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.class, "AttributeValue not supported for spec " + spec);
            return null;
        }

        return attributeValue;
    }

    private KmipDataType deserializeObjects(String nodeName, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!node.has("type") || !node.has("value")) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing 'type', or 'value' field in JSON");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipSpec spec = KmipContext.getSpec();
        KmipTag tag;
        try {
            tag = new KmipTag(KmipTag.fromName(spec, nodeName));
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

        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(tag.getValue(), encodingType);

        return p.getCodec().treeToValue(node, clazz);
    }
}
