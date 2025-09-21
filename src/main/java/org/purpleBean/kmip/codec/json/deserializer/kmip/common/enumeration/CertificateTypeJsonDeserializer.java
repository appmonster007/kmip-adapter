package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for CertificateType.
 */
public class CertificateTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public CertificateType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CertificateType.class, "JSON node cannot be null for CertificateType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateType.class, "Invalid KMIP tag for CertificateType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateType.class, String.format("Failed to parse KMIP tag for CertificateType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(CertificateType.class,
                    String.format("Expected object with %s tag for CertificateType, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateType.class, "Missing or non-text 'type' field for CertificateType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(CertificateType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        CertificateType.Value certificatetypeValue;
        try {
            certificatetypeValue = CertificateType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(CertificateType.class,
                    String.format("Unknown CertificateType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        CertificateType certificatetype = new CertificateType(certificatetypeValue);

        // Final validation: Ensure constructed CertificateType is supported
        if (!certificatetype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("CertificateType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return certificatetype;
    }
}
