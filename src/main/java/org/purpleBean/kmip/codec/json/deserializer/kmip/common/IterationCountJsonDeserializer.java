package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IterationCount;

import java.io.IOException;

public class IterationCountJsonDeserializer extends KmipDataTypeJsonDeserializer<IterationCount> {
    private final KmipTag kmipTag = IterationCount.kmipTag;
    private final EncodingType encodingType = IterationCount.encodingType;

    @Override
    public IterationCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(IterationCount.class, String.format("JSON node cannot be null for IterationCount deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(IterationCount.class, String.format("Invalid KMIP tag for IterationCount"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(IterationCount.class, String.format("Failed to parse KMIP tag for IterationCount: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(IterationCount.class,
                    String.format("Expected object with %s tag for IterationCount, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(IterationCount.class, String.format("Missing or non-text 'type' field for IterationCount"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(IterationCount.class, "IterationCount 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        IterationCount iterationCount = IterationCount.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!iterationCount.isSupported()) {
            ctxt.reportInputMismatch(IterationCount.class, "IterationCount not supported for spec " + spec);
            return null;
        }

        return iterationCount;
    }
}