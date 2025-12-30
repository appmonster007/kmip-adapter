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

public class AttributeValueBooleanJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue.Boolean> {
    private final KmipTag kmipTag = AttributeValue.Boolean.kmipTag;
    private final EncodingType encodingType = AttributeValue.Boolean.encodingType;

    @Override
    public AttributeValue.Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class, String.format("JSON node cannot be null for AttributeValue.Boolean deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.Boolean.class, String.format("Invalid KMIP tag for AttributeValue.Boolean"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class, String.format("Failed to parse KMIP tag for AttributeValue.Boolean: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class,
                    String.format("Expected object with %s tag for AttributeValue.Boolean, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class, String.format("Missing or non-text 'type' field for AttributeValue.Boolean"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isBoolean()) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class, "AttributeValue.Boolean 'value' must be a non-empty boolean");
            return null;
        }

        java.lang.Boolean value = p.getCodec().treeToValue(valueNode, java.lang.Boolean.class);
        AttributeValue.Boolean attributeValueBoolean = AttributeValue.Boolean.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueBoolean.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.Boolean.class, "AttributeValue.Boolean not supported for spec " + spec);
            return null;
        }

        return attributeValueBoolean;
    }
}
