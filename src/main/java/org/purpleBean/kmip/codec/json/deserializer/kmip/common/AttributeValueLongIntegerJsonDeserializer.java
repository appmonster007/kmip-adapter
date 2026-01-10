package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

import java.io.IOException;

public class AttributeValueLongIntegerJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueLongInteger> {
    private final KmipTag kmipTag = AttributeValueLongInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueLongInteger.encodingType;

    @Override
    public AttributeValueLongInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, String.format("JSON node cannot be null for AttributeValue.LongInteger deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueLongInteger.class, String.format("Invalid KMIP tag for AttributeValue.LongInteger"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, String.format("Failed to parse KMIP tag for AttributeValue.LongInteger: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class,
                    String.format("Expected object with %s tag for AttributeValue.LongInteger, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, String.format("Missing or non-text 'type' field for AttributeValue.LongInteger"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "AttributeValue.LongInteger 'value' must be a non-empty number");
            return null;
        }

        java.lang.Long value = p.getCodec().treeToValue(valueNode, java.lang.Long.class);
        AttributeValueLongInteger attributeValueLongInteger = AttributeValueLongInteger.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueLongInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "AttributeValue.LongInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueLongInteger;
    }
}
