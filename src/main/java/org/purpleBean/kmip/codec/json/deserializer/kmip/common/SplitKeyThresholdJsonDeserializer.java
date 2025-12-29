package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

import java.io.IOException;

public class SplitKeyThresholdJsonDeserializer extends KmipDataTypeJsonDeserializer<SplitKeyThreshold> {
    private final KmipTag kmipTag = SplitKeyThreshold.kmipTag;
    private final EncodingType encodingType = SplitKeyThreshold.encodingType;

    @Override
    public SplitKeyThreshold deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, String.format("JSON node cannot be null for SplitKeyThreshold deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SplitKeyThreshold.class, String.format("Invalid KMIP tag for SplitKeyThreshold"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, String.format("Failed to parse KMIP tag for SplitKeyThreshold: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class,
                    String.format("Expected object with %s tag for SplitKeyThreshold, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, String.format("Missing or non-text 'type' field for SplitKeyThreshold"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "SplitKeyThreshold 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        SplitKeyThreshold splitKeyThreshold = SplitKeyThreshold.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!splitKeyThreshold.isSupported()) {
            ctxt.reportInputMismatch(SplitKeyThreshold.class, "SplitKeyThreshold not supported for spec " + spec);
            return null;
        }

        return splitKeyThreshold;
    }
}