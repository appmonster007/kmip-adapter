package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentEcdsaPublicKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentEcdsaPublicKey> {
    private final KmipTag kmipTag = TransparentEcdsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentEcdsaPublicKey.encodingType;

    @Override
    public TransparentEcdsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, String.format("JSON node cannot be null for TransparentEcdsaPublicKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, String.format("Invalid KMIP tag for TransparentEcdsaPublicKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, String.format("Failed to parse KMIP tag for TransparentEcdsaPublicKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class,
                    String.format("Expected object with %s tag for TransparentEcdsaPublicKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, String.format("Missing or non-text 'type' field for TransparentEcdsaPublicKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentEcdsaPublicKey.class, "TransparentEcdsaPublicKey 'value' must be a non-empty array");
            return null;
        }

        TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder = TransparentEcdsaPublicKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentEcdsaPublicKey transparentEcdsaPublicKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentEcdsaPublicKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentEcdsaPublicKey is not supported for KMIP spec %s", spec));
        }

        return transparentEcdsaPublicKey;
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
    private void setValue(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(p.getCodec().treeToValue(node, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(p.getCodec().treeToValue(node, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}