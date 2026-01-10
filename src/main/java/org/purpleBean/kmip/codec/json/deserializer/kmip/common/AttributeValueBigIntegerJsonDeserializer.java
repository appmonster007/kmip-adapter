package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.io.IOException;
import java.math.BigInteger;

public class AttributeValueBigIntegerJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueBigInteger> {
    private final KmipTag kmipTag = AttributeValueBigInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueBigInteger.encodingType;

    @Override
    public AttributeValueBigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, String.format("JSON node cannot be null for AttributeValue.BigInteger deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueBigInteger.class, String.format("Invalid KMIP tag for AttributeValue.BigInteger"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, String.format("Failed to parse KMIP tag for AttributeValue.BigInteger: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class,
                    String.format("Expected object with %s tag for AttributeValue.BigInteger, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, String.format("Missing or non-text 'type' field for AttributeValue.BigInteger"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "AttributeValue.BigInteger 'value' must be a non-empty number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        AttributeValueBigInteger attributeValueBigInteger = AttributeValueBigInteger.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueBigInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "AttributeValue.BigInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueBigInteger;
    }
}
