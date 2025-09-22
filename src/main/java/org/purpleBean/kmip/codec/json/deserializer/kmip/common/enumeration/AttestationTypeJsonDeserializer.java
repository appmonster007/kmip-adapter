package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for AttestationType.
 */
public class AttestationTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<AttestationType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTESTATION_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public AttestationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(AttestationType.class, "JSON node cannot be null for AttestationType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttestationType.class, "Invalid KMIP tag for AttestationType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttestationType.class, String.format("Failed to parse KMIP tag for AttestationType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttestationType.class,
                    String.format("Expected object with %s tag for AttestationType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttestationType.class, "Missing or non-text 'type' field for AttestationType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttestationType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(AttestationType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        AttestationType.Value attestationtypeValue;
        try {
            attestationtypeValue = AttestationType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(AttestationType.class,
                    String.format("Unknown AttestationType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        AttestationType attestationtype = new AttestationType(attestationtypeValue);

        // Final validation: Ensure constructed AttestationType is supported
        if (!attestationtype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("AttestationType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return attestationtype;
    }
}
