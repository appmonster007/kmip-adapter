package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for OtpAlgorithm.
 */
public class OtpAlgorithmJsonDeserializer extends KmipDataTypeJsonDeserializer<OtpAlgorithm> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OTP_ALGORITHM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public OtpAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "JSON node cannot be null for OtpAlgorithm deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(OtpAlgorithm.class, "Invalid KMIP tag for OtpAlgorithm");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, String.format("Failed to parse KMIP tag for OtpAlgorithm: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(OtpAlgorithm.class,
                    String.format("Expected object with %s tag for OtpAlgorithm, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Missing or non-text 'type' field for OtpAlgorithm");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        OtpAlgorithm.Value otpalgorithmValue;
        try {
            otpalgorithmValue = OtpAlgorithm.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(OtpAlgorithm.class,
                    String.format("Unknown OtpAlgorithm value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        OtpAlgorithm otpalgorithm = new OtpAlgorithm(otpalgorithmValue);

        // Final validation: Ensure constructed OtpAlgorithm is supported
        if (!otpalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("OtpAlgorithm '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return otpalgorithm;
    }
}
