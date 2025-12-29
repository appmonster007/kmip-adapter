package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;

import java.io.IOException;
import java.util.NoSuchElementException;

public class X509CertificateIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<X509CertificateIdentifier> {
    private final KmipTag kmipTag = X509CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = X509CertificateIdentifier.encodingType;

    @Override
    public X509CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "JSON node cannot be null for X509CertificateIdentifier deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(X509CertificateIdentifier.class, "Invalid KMIP tag for X509CertificateIdentifier");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, String.format("Failed to parse KMIP tag for X509CertificateIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class,
                    String.format("Expected object with %s tag for X509CertificateIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, String.format("Missing or non-text 'type' field for X509CertificateIdentifier"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "X509CertificateIdentifier 'value' must be a non-empty array");
            return null;
        }

        X509CertificateIdentifier.X509CertificateIdentifierBuilder builder = X509CertificateIdentifier.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(X509CertificateIdentifier.class, String.format("Failed to process field in X509CertificateIdentifier: %s", e.getMessage()));
                return null;
            }
        }

        X509CertificateIdentifier x509CertificateIdentifier = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!x509CertificateIdentifier.isSupported()) {
            throw new NoSuchElementException(String.format("X509CertificateIdentifier is not supported for KMIP spec %s", spec));
        }

        return x509CertificateIdentifier;
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
        X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
        KmipTag.Value nodeTag,
        JsonNode node,
        JsonParser p,
        DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME -> 
                builder.issuerDistinguishedName(p.getCodec().treeToValue(node, IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER -> 
                builder.certificateSerialNumber(p.getCodec().treeToValue(node, CertificateSerialNumber.class));
            default -> 
                throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
