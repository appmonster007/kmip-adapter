package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

import java.io.IOException;

public class MaximumResponseSizeJsonDeserializer extends KmipDataTypeJsonDeserializer<MaximumResponseSize> {
    private final KmipTag kmipTag = MaximumResponseSize.kmipTag;
    private final EncodingType encodingType = MaximumResponseSize.encodingType;

    @Override
    public MaximumResponseSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, String.format("JSON node cannot be null for MaximumResponseSize deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MaximumResponseSize.class, String.format("Invalid KMIP tag for MaximumResponseSize"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, String.format("Failed to parse KMIP tag for MaximumResponseSize: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class,
                    String.format("Expected object with %s tag for MaximumResponseSize, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, String.format("Missing or non-text 'type' field for MaximumResponseSize"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "MaximumResponseSize 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        MaximumResponseSize maximumResponseSize = MaximumResponseSize.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!maximumResponseSize.isSupported()) {
            ctxt.reportInputMismatch(MaximumResponseSize.class, "MaximumResponseSize not supported for spec " + spec);
            return null;
        }

        return maximumResponseSize;
    }
}