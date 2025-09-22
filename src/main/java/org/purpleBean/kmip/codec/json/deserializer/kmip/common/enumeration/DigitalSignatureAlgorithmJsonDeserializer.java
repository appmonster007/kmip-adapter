package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for DigitalSignatureAlgorithm.
 */
public class DigitalSignatureAlgorithmJsonDeserializer extends KmipDataTypeJsonDeserializer<DigitalSignatureAlgorithm> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public DigitalSignatureAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "JSON node cannot be null for DigitalSignatureAlgorithm deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Invalid KMIP tag for DigitalSignatureAlgorithm");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, String.format("Failed to parse KMIP tag for DigitalSignatureAlgorithm: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class,
                    String.format("Expected object with %s tag for DigitalSignatureAlgorithm, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, "Missing or non-text 'type' field for DigitalSignatureAlgorithm");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        DigitalSignatureAlgorithm.Value digitalsignaturealgorithmValue;
        try {
            digitalsignaturealgorithmValue = DigitalSignatureAlgorithm.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(DigitalSignatureAlgorithm.class,
                    String.format("Unknown DigitalSignatureAlgorithm value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        DigitalSignatureAlgorithm digitalsignaturealgorithm = new DigitalSignatureAlgorithm(digitalsignaturealgorithmValue);

        // Final validation: Ensure constructed DigitalSignatureAlgorithm is supported
        if (!digitalsignaturealgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("DigitalSignatureAlgorithm '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return digitalsignaturealgorithm;
    }
}
