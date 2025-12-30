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

public class AttributeValueTextStringJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue.TextString> {
    private final KmipTag kmipTag = AttributeValue.TextString.kmipTag;
    private final EncodingType encodingType = AttributeValue.TextString.encodingType;

    @Override
    public AttributeValue.TextString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, String.format("JSON node cannot be null for AttributeValue.TextString deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.TextString.class, String.format("Invalid KMIP tag for AttributeValue.TextString"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, String.format("Failed to parse KMIP tag for AttributeValue.TextString: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class,
                    String.format("Expected object with %s tag for AttributeValue.TextString, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, String.format("Missing or non-text 'type' field for AttributeValue.TextString"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "AttributeValue.TextString 'value' must be a non-empty string");
            return null;
        }

        String value = p.getCodec().treeToValue(valueNode, String.class);
        AttributeValue.TextString attributeValueTextString = AttributeValue.TextString.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueTextString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "AttributeValue.TextString not supported for spec " + spec);
            return null;
        }

        return attributeValueTextString;
    }
}
