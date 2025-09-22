package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ProcessingStage.
 */
public class ProcessingStageJsonDeserializer extends KmipDataTypeJsonDeserializer<ProcessingStage> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROCESSING_STAGE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ProcessingStage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ProcessingStage.class, "JSON node cannot be null for ProcessingStage deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ProcessingStage.class, "Invalid KMIP tag for ProcessingStage");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ProcessingStage.class, String.format("Failed to parse KMIP tag for ProcessingStage: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ProcessingStage.class,
                    String.format("Expected object with %s tag for ProcessingStage, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ProcessingStage.class, "Missing or non-text 'type' field for ProcessingStage");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProcessingStage.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ProcessingStage.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ProcessingStage.Value processingstageValue;
        try {
            processingstageValue = ProcessingStage.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ProcessingStage.class,
                    String.format("Unknown ProcessingStage value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ProcessingStage processingstage = new ProcessingStage(processingstageValue);

        // Final validation: Ensure constructed ProcessingStage is supported
        if (!processingstage.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ProcessingStage '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return processingstage;
    }
}
