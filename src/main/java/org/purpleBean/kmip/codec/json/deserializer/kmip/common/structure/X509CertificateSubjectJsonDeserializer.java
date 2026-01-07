package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import java.io.IOException;
import java.util.NoSuchElementException;

public class X509CertificateSubjectJsonDeserializer extends KmipDataTypeJsonDeserializer<X509CertificateSubject> {
    private final KmipTag kmipTag = X509CertificateSubject.kmipTag;
    private final EncodingType encodingType = X509CertificateSubject.encodingType;

    @Override
    public X509CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "JSON node cannot be null for X509CertificateSubject deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(X509CertificateSubject.class, "Invalid KMIP tag for X509CertificateSubject");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, String.format("Failed to parse KMIP tag for X509CertificateSubject: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(X509CertificateSubject.class,
                    String.format("Expected object with %s tag for X509CertificateSubject, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, String.format("Missing or non-text 'type' field for X509CertificateSubject"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "X509CertificateSubject 'value' must be a non-empty array");
            return null;
        }

        X509CertificateSubject.X509CertificateSubjectBuilder builder = X509CertificateSubject.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(X509CertificateSubject.class, String.format("Failed to process field in X509CertificateSubject: %s", e.getMessage()));
                return null;
            }
        }

        X509CertificateSubject x509certificatesubject = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!x509certificatesubject.isSupported()) {
            throw new NoSuchElementException(String.format("X509CertificateSubject is not supported for KMIP spec %s", spec));
        }

        return x509certificatesubject;
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
            X509CertificateSubject.X509CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
                    builder.subjectDistinguishedName(p.getCodec().treeToValue(node, SubjectDistinguishedName.class));
            case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
                    builder.subjectAlternativeName(p.getCodec().treeToValue(node, SubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}