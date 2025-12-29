package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

import java.io.IOException;

public class CertificateIssuerAlternativeNameJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateIssuerAlternativeName> {
    private final KmipTag kmipTag = CertificateIssuerAlternativeName.kmipTag;
    private final EncodingType encodingType = CertificateIssuerAlternativeName.encodingType;

    @Override
    public CertificateIssuerAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, String.format("JSON node cannot be null for CertificateIssuerAlternativeName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, String.format("Invalid KMIP tag for CertificateIssuerAlternativeName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, String.format("Failed to parse KMIP tag for CertificateIssuerAlternativeName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class,
                    String.format("Expected object with %s tag for CertificateIssuerAlternativeName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, String.format("Missing or non-text 'type' field for CertificateIssuerAlternativeName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "CertificateIssuerAlternativeName 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        CertificateIssuerAlternativeName certificateIssuerAlternativeName = CertificateIssuerAlternativeName.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateIssuerAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(CertificateIssuerAlternativeName.class, "CertificateIssuerAlternativeName not supported for spec " + spec);
            return null;
        }

        return certificateIssuerAlternativeName;
    }
}