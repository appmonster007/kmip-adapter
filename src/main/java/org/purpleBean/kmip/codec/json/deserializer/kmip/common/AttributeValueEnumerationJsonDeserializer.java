package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

import java.io.IOException;

public class AttributeValueEnumerationJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueEnumeration> {
    private final KmipTag kmipTag = AttributeValueEnumeration.kmipTag;
    private final EncodingType encodingType = AttributeValueEnumeration.encodingType;

    @Override
    public AttributeValueEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, String.format("JSON node cannot be null for AttributeValue.Enumeration deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueEnumeration.class, String.format("Invalid KMIP tag for AttributeValue.Enumeration"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, String.format("Failed to parse KMIP tag for AttributeValue.Enumeration: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class,
                    String.format("Expected object with %s tag for AttributeValue.Enumeration, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, String.format("Missing or non-text 'type' field for AttributeValue.Enumeration"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "AttributeValue.Enumeration 'value' must be a non-empty number");
            return null;
        }

        java.lang.Integer value = p.getCodec().treeToValue(valueNode, java.lang.Integer.class);
        AttributeValueEnumeration attributeValueEnumeration = AttributeValueEnumeration.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueEnumeration.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "AttributeValue.Enumeration not supported for spec " + spec);
            return null;
        }

        return attributeValueEnumeration;
    }
}
