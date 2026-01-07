package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.CertificateSubject;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CertificateSubjectJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateSubject> {
    private final KmipTag kmipTag = CertificateSubject.kmipTag;
    private final EncodingType encodingType = CertificateSubject.encodingType;

    @Override
    public CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CertificateSubject.class, "JSON node cannot be null for CertificateSubject deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateSubject.class, "Invalid KMIP tag for CertificateSubject");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateSubject.class, String.format("Failed to parse KMIP tag for CertificateSubject: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateSubject.class,
                    String.format("Expected object with %s tag for CertificateSubject, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateSubject.class, String.format("Missing or non-text 'type' field for CertificateSubject"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(CertificateSubject.class, "CertificateSubject 'value' must be a non-empty array");
            return null;
        }

        CertificateSubject.CertificateSubjectBuilder builder = CertificateSubject.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(CertificateSubject.class, String.format("Failed to process field in CertificateSubject: %s", e.getMessage()));
                return null;
            }
        }

        CertificateSubject certificatesubject = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificatesubject.isSupported()) {
            throw new NoSuchElementException(String.format("CertificateSubject is not supported for KMIP spec %s", spec));
        }

        return certificatesubject;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            CertificateSubject.CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
                    builder.certificateSubjectDistinguishedName(p.getCodec().treeToValue(node, CertificateSubjectDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
                    builder.certificateSubjectAlternativeName(p.getCodec().treeToValue(node, CertificateSubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}