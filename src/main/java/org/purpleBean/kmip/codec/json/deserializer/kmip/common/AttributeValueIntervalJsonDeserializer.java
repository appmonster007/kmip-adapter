package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

import java.io.IOException;

public class AttributeValueIntervalJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueInterval> {
    private final KmipTag kmipTag = AttributeValueInterval.kmipTag;
    private final EncodingType encodingType = AttributeValueInterval.encodingType;

    @Override
    public AttributeValueInterval deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, String.format("JSON node cannot be null for AttributeValue.Interval deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueInterval.class, String.format("Invalid KMIP tag for AttributeValue.Interval"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, String.format("Failed to parse KMIP tag for AttributeValue.Interval: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class,
                    String.format("Expected object with %s tag for AttributeValue.Interval, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, String.format("Missing or non-text 'type' field for AttributeValue.Interval"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "AttributeValue.Interval 'value' must be a non-empty number");
            return null;
        }

        java.lang.Integer value = p.getCodec().treeToValue(valueNode, java.lang.Integer.class);
        AttributeValueInterval attributeValueInterval = AttributeValueInterval.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueInterval.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "AttributeValue.Interval not supported for spec " + spec);
            return null;
        }

        return attributeValueInterval;
    }
}
