package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

import java.io.IOException;

public class CertificateSubjectDistinguishedNameJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateSubjectDistinguishedName> {
    private final KmipTag kmipTag = CertificateSubjectDistinguishedName.kmipTag;
    private final EncodingType encodingType = CertificateSubjectDistinguishedName.encodingType;

    @Override
    public CertificateSubjectDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, String.format("JSON node cannot be null for CertificateSubjectDistinguishedName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, String.format("Invalid KMIP tag for CertificateSubjectDistinguishedName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, String.format("Failed to parse KMIP tag for CertificateSubjectDistinguishedName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class,
                    String.format("Expected object with %s tag for CertificateSubjectDistinguishedName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, String.format("Missing or non-text 'type' field for CertificateSubjectDistinguishedName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "CertificateSubjectDistinguishedName 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        CertificateSubjectDistinguishedName certificateSubjectDistinguishedName = CertificateSubjectDistinguishedName.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateSubjectDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(CertificateSubjectDistinguishedName.class, "CertificateSubjectDistinguishedName not supported for spec " + spec);
            return null;
        }

        return certificateSubjectDistinguishedName;
    }
}