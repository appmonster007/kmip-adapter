package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for CertificateRequestType.
 */
public class CertificateRequestTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateRequestType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_REQUEST_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public CertificateRequestType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "JSON node cannot be null for CertificateRequestType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateRequestType.class, "Invalid KMIP tag for CertificateRequestType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateRequestType.class, String.format("Failed to parse KMIP tag for CertificateRequestType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(CertificateRequestType.class,
                    String.format("Expected object with %s tag for CertificateRequestType, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateRequestType.class, "Missing or non-text 'type' field for CertificateRequestType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateRequestType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(CertificateRequestType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        CertificateRequestType.Value certificaterequesttypeValue;
        try {
            certificaterequesttypeValue = CertificateRequestType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(CertificateRequestType.class,
                    String.format("Unknown CertificateRequestType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        CertificateRequestType certificaterequesttype = new CertificateRequestType(certificaterequesttypeValue);

        // Final validation: Ensure constructed CertificateRequestType is supported
        if (!certificaterequesttype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("CertificateRequestType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return certificaterequesttype;
    }
}
