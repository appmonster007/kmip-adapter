package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentRsaPrivateKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentRsaPrivateKey> {
    private final KmipTag kmipTag = TransparentRsaPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentRsaPrivateKey.encodingType;

    @Override
    public TransparentRsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, String.format("JSON node cannot be null for TransparentRsaPrivateKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, String.format("Invalid KMIP tag for TransparentRsaPrivateKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, String.format("Failed to parse KMIP tag for TransparentRsaPrivateKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class,
                    String.format("Expected object with %s tag for TransparentRsaPrivateKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, String.format("Missing or non-text 'type' field for TransparentRsaPrivateKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "TransparentRsaPrivateKey 'value' must be a non-empty array");
            return null;
        }

        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder = TransparentRsaPrivateKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentRsaPrivateKey transparentRsaPrivateKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentRsaPrivateKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentRsaPrivateKey is not supported for KMIP spec %s", spec));
        }

        return transparentRsaPrivateKey;
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
    private void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(p.getCodec().treeToValue(node, Modulus.class));
            case KmipTag.Standard.PRIVATE_EXPONENT ->
                    builder.privateExponent(p.getCodec().treeToValue(node, PrivateExponent.class));
            case KmipTag.Standard.PUBLIC_EXPONENT ->
                    builder.publicExponent(p.getCodec().treeToValue(node, PublicExponent.class));
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.PRIME_EXPONENT_P ->
                    builder.primeExponentP(p.getCodec().treeToValue(node, PrimeExponentP.class));
            case KmipTag.Standard.PRIME_EXPONENT_Q ->
                    builder.primeExponentQ(p.getCodec().treeToValue(node, PrimeExponentQ.class));
            case KmipTag.Standard.CRT_COEFFICIENT ->
                    builder.crtCoefficient(p.getCodec().treeToValue(node, CRTCoefficient.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}