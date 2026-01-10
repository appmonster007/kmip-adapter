package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

import java.io.IOException;
import java.util.NoSuchElementException;

public class X509CertificateIssuerJsonDeserializer extends KmipDataTypeJsonDeserializer<X509CertificateIssuer> {
    private final KmipTag kmipTag = X509CertificateIssuer.kmipTag;
    private final EncodingType encodingType = X509CertificateIssuer.encodingType;

    @Override
    public X509CertificateIssuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "JSON node cannot be null for X509CertificateIssuer deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(X509CertificateIssuer.class, "Invalid KMIP tag for X509CertificateIssuer");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, String.format("Failed to parse KMIP tag for X509CertificateIssuer: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class,
                    String.format("Expected object with %s tag for X509CertificateIssuer, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, String.format("Missing or non-text 'type' field for X509CertificateIssuer"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "X509CertificateIssuer 'value' must be a non-empty array");
            return null;
        }

        X509CertificateIssuer.X509CertificateIssuerBuilder builder = X509CertificateIssuer.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(X509CertificateIssuer.class, String.format("Failed to process field in X509CertificateIssuer: %s", e.getMessage()));
                return null;
            }
        }

        X509CertificateIssuer x509certificateissuer = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!x509certificateissuer.isSupported()) {
            throw new NoSuchElementException(String.format("X509CertificateIssuer is not supported for KMIP spec %s", spec));
        }

        return x509certificateissuer;
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
            X509CertificateIssuer.X509CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(p.getCodec().treeToValue(node, IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(p.getCodec().treeToValue(node, IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}