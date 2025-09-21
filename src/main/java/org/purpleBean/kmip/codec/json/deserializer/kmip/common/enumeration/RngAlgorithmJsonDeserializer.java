package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for RngAlgorithm.
 */
public class RngAlgorithmJsonDeserializer extends KmipDataTypeJsonDeserializer<RngAlgorithm> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RNG_ALGORITHM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public RngAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "JSON node cannot be null for RngAlgorithm deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RngAlgorithm.class, "Invalid KMIP tag for RngAlgorithm");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RngAlgorithm.class, String.format("Failed to parse KMIP tag for RngAlgorithm: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(RngAlgorithm.class,
                    String.format("Expected object with %s tag for RngAlgorithm, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Missing or non-text 'type' field for RngAlgorithm");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RngAlgorithm.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(RngAlgorithm.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        RngAlgorithm.Value rngalgorithmValue;
        try {
            rngalgorithmValue = RngAlgorithm.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(RngAlgorithm.class,
                    String.format("Unknown RngAlgorithm value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        RngAlgorithm rngalgorithm = new RngAlgorithm(rngalgorithmValue);

        // Final validation: Ensure constructed RngAlgorithm is supported
        if (!rngalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("RngAlgorithm '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return rngalgorithm;
    }
}
