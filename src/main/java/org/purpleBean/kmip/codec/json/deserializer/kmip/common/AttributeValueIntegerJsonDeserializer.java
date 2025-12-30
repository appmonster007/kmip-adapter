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

public class AttributeValueIntegerJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue.Integer> {
    private final KmipTag kmipTag = AttributeValue.Integer.kmipTag;
    private final EncodingType encodingType = AttributeValue.Integer.encodingType;

    @Override
    public AttributeValue.Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class, String.format("JSON node cannot be null for AttributeValue.Integer deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.Integer.class, String.format("Invalid KMIP tag for AttributeValue.Integer"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class, String.format("Failed to parse KMIP tag for AttributeValue.Integer: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class,
                    String.format("Expected object with %s tag for AttributeValue.Integer, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class, String.format("Missing or non-text 'type' field for AttributeValue.Integer"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class, "AttributeValue.Integer 'value' must be a non-empty number");
            return null;
        }

        java.lang.Integer value = p.getCodec().treeToValue(valueNode, java.lang.Integer.class);
        AttributeValue.Integer attributeValueInteger = AttributeValue.Integer.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.Integer.class, "AttributeValue.Integer not supported for spec " + spec);
            return null;
        }

        return attributeValueInteger;
    }
}
