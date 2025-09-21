package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ResultStatus.
 */
public class ResultStatusJsonDeserializer extends KmipDataTypeJsonDeserializer<ResultStatus> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RESULT_STATUS);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ResultStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ResultStatus.class, "JSON node cannot be null for ResultStatus deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ResultStatus.class, "Invalid KMIP tag for ResultStatus");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ResultStatus.class, String.format("Failed to parse KMIP tag for ResultStatus: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(ResultStatus.class,
                    String.format("Expected object with %s tag for ResultStatus, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ResultStatus.class, "Missing or non-text 'type' field for ResultStatus");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ResultStatus.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ResultStatus.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ResultStatus.Value resultstatusValue;
        try {
            resultstatusValue = ResultStatus.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ResultStatus.class,
                    String.format("Unknown ResultStatus value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ResultStatus resultstatus = new ResultStatus(resultstatusValue);

        // Final validation: Ensure constructed ResultStatus is supported
        if (!resultstatus.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ResultStatus '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return resultstatus;
    }
}
