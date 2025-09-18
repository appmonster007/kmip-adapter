package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for BatchErrorContinuationOption.
 */
public class BatchErrorContinuationOptionJsonDeserializer extends KmipDataTypeJsonDeserializer<BatchErrorContinuationOption> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.BATCH_ERROR_CONTINUATION_OPTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public BatchErrorContinuationOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("JSON node cannot be null for BatchErrorContinuationOption deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("Invalid KMIP tag for BatchErrorContinuationOption"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("Failed to parse KMIP tag for BatchErrorContinuationOption: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class,
                    String.format("Expected object with %s tag for BatchErrorContinuationOption, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("Missing or non-text 'type' field for BatchErrorContinuationOption"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        BatchErrorContinuationOption.Value batcherrorcontinuationoptionValue;
        try {
            batcherrorcontinuationoptionValue = BatchErrorContinuationOption.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class,
                    String.format("Unknown BatchErrorContinuationOption value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        BatchErrorContinuationOption batcherrorcontinuationoption = new BatchErrorContinuationOption(batcherrorcontinuationoptionValue);

        // Final validation: Ensure constructed BatchErrorContinuationOption is supported
        if (!batcherrorcontinuationoption.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("BatchErrorContinuationOption '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return batcherrorcontinuationoption;
    }
}
