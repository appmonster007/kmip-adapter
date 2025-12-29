package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.BatchCount;

import java.io.IOException;

public class BatchCountJsonDeserializer extends KmipDataTypeJsonDeserializer<BatchCount> {
    private final KmipTag kmipTag = BatchCount.kmipTag;
    private final EncodingType encodingType = BatchCount.encodingType;

    @Override
    public BatchCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(BatchCount.class, String.format("JSON node cannot be null for BatchCount deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(BatchCount.class, String.format("Invalid KMIP tag for BatchCount"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(BatchCount.class, String.format("Failed to parse KMIP tag for BatchCount: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(BatchCount.class,
                    String.format("Expected object with %s tag for BatchCount, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(BatchCount.class, String.format("Missing or non-text 'type' field for BatchCount"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(BatchCount.class, "BatchCount 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        BatchCount batchCount = BatchCount.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!batchCount.isSupported()) {
            ctxt.reportInputMismatch(BatchCount.class, "BatchCount not supported for spec " + spec);
            return null;
        }

        return batchCount;
    }
}