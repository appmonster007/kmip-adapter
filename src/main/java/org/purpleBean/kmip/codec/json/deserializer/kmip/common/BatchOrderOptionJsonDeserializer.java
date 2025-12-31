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
import java.util.NoSuchElementException;

/**
 * JSON deserializer for BatchOrderOption.
 */
public class BatchOrderOptionJsonDeserializer extends KmipDataTypeJsonDeserializer<BatchOrderOption> {
    private final KmipTag kmipTag = BatchOrderOption.kmipTag;
    private final EncodingType encodingType = BatchOrderOption.encodingType;

    @Override
    public BatchOrderOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(BatchOrderOption.class, "JSON node cannot be null for BatchOrderOption deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(BatchOrderOption.class, "Invalid KMIP tag for BatchOrderOption");
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
            ctxt.reportInputMismatch(BatchOrderOption.class, "Missing or non-text 'type' field for BatchOrderOption");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String value = valueNode.asText();
        if (value == null || value.trim().isEmpty()) {
            ctxt.reportInputMismatch(BatchOrderOption.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        BatchOrderOption attribute = BatchOrderOption.of(Boolean.valueOf(value));

        // Final validation: Ensure constructed BatchOrderOption is supported
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("BatchOrderOption '%s' is not supported for KMIP spec %s", value, spec)
            );
        }

        return attribute;
    }
}
