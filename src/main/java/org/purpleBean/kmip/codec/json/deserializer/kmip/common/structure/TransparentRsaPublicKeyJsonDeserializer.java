package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentRsaPublicKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentRsaPublicKey> {
    private final KmipTag kmipTag = TransparentRsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentRsaPublicKey.encodingType;

    @Override
    public TransparentRsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, String.format("JSON node cannot be null for TransparentRsaPublicKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentRsaPublicKey.class, String.format("Invalid KMIP tag for TransparentRsaPublicKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, String.format("Failed to parse KMIP tag for TransparentRsaPublicKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class,
                    String.format("Expected object with %s tag for TransparentRsaPublicKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, String.format("Missing or non-text 'type' field for TransparentRsaPublicKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentRsaPublicKey.class, "TransparentRsaPublicKey 'value' must be a non-empty array");
            return null;
        }

        TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder = TransparentRsaPublicKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentRsaPublicKey transparentRsaPublicKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentRsaPublicKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentRsaPublicKey is not supported for KMIP spec %s", spec));
        }

        return transparentRsaPublicKey;
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
    private void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(p.getCodec().treeToValue(node, Modulus.class));
            case KmipTag.Standard.PUBLIC_EXPONENT ->
                    builder.publicExponent(p.getCodec().treeToValue(node, PublicExponent.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}