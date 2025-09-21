package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for CancellationResult.
 */
public class CancellationResultJsonDeserializer extends KmipDataTypeJsonDeserializer<CancellationResult> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CANCELLATION_RESULT);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public CancellationResult deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CancellationResult.class, "JSON node cannot be null for CancellationResult deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CancellationResult.class, "Invalid KMIP tag for CancellationResult");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CancellationResult.class, String.format("Failed to parse KMIP tag for CancellationResult: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(CancellationResult.class,
                    String.format("Expected object with %s tag for CancellationResult, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CancellationResult.class, "Missing or non-text 'type' field for CancellationResult");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CancellationResult.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(CancellationResult.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        CancellationResult.Value cancellationresultValue;
        try {
            cancellationresultValue = CancellationResult.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(CancellationResult.class,
                    String.format("Unknown CancellationResult value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        CancellationResult cancellationresult = new CancellationResult(cancellationresultValue);

        // Final validation: Ensure constructed CancellationResult is supported
        if (!cancellationresult.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("CancellationResult '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return cancellationresult;
    }
}
