package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CertificateIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateIdentifier> {
    private final KmipTag kmipTag = CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = CertificateIdentifier.encodingType;

    @Override
    public CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "JSON node cannot be null for CertificateIdentifier deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateIdentifier.class, "Invalid KMIP tag for CertificateIdentifier");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, String.format("Failed to parse KMIP tag for CertificateIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateIdentifier.class,
                    String.format("Expected object with %s tag for CertificateIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, String.format("Missing or non-text 'type' field for CertificateIdentifier"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "CertificateIdentifier 'value' must be a non-empty array");
            return null;
        }

        CertificateIdentifier.CertificateIdentifierBuilder builder = CertificateIdentifier.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(CertificateIdentifier.class, String.format("Failed to process field in CertificateIdentifier: %s", e.getMessage()));
                return null;
            }
        }

        CertificateIdentifier certificateidentifier = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateidentifier.isSupported()) {
            throw new NoSuchElementException(String.format("CertificateIdentifier is not supported for KMIP spec %s", spec));
        }

        return certificateidentifier;
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
            CertificateIdentifier.CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(p.getCodec().treeToValue(node, Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER ->
                    builder.serialNumber(p.getCodec().treeToValue(node, SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}