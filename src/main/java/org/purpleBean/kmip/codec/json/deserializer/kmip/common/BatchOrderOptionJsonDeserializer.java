package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.BatchOrderOption;

import java.io.IOException;

public class BatchOrderOptionJsonDeserializer extends KmipDataTypeJsonDeserializer<BatchOrderOption> {
    private final KmipTag kmipTag = BatchOrderOption.kmipTag;
    private final EncodingType encodingType = BatchOrderOption.encodingType;

    @Override
    public BatchOrderOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(BatchOrderOption.class, String.format("JSON node cannot be null for BatchOrderOption deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(BatchOrderOption.class, String.format("Invalid KMIP tag for BatchOrderOption"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(BatchOrderOption.class, String.format("Failed to parse KMIP tag for BatchOrderOption: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(BatchOrderOption.class,
                    String.format("Expected object with %s tag for BatchOrderOption, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(BatchOrderOption.class, String.format("Missing or non-text 'type' field for BatchOrderOption"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isBoolean()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "BatchOrderOption 'value' must be a boolean");
            return null;
        }

        boolean value = valueNode.asBoolean();
        BatchOrderOption batchOrderOption = BatchOrderOption.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!batchOrderOption.isSupported()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "BatchOrderOption not supported for spec " + spec);
            return null;
        }

        return batchOrderOption;
    }
}