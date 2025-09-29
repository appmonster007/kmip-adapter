package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ResultReason.
 */
public class ResultReasonJsonDeserializer extends KmipDataTypeJsonDeserializer<ResultReason> {
    private final KmipTag kmipTag = ResultReason.kmipTag;
    private final EncodingType encodingType = ResultReason.encodingType;

    @Override
    public ResultReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ResultReason.class, "JSON node cannot be null for ResultReason deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ResultReason.class, "Invalid KMIP tag for ResultReason");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ResultReason.class, String.format("Failed to parse KMIP tag for ResultReason: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ResultReason.class,
                    String.format("Expected object with %s tag for ResultReason, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ResultReason.class, "Missing or non-text 'type' field for ResultReason");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ResultReason.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ResultReason.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ResultReason.Value resultreasonValue;
        try {
            resultreasonValue = ResultReason.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ResultReason.class,
                    String.format("Unknown ResultReason value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ResultReason resultreason = new ResultReason(resultreasonValue);

        // Final validation: Ensure constructed ResultReason is supported
        if (!resultreason.isSupported()) {
            throw new NoSuchElementException(
                    String.format("ResultReason '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return resultreason;
    }
}
