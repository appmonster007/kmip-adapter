package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentDsaPublicKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentDsaPublicKey> {
    private final KmipTag kmipTag = TransparentDsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentDsaPublicKey.encodingType;

    @Override
    public TransparentDsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, String.format("JSON node cannot be null for TransparentDsaPublicKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentDsaPublicKey.class, String.format("Invalid KMIP tag for TransparentDsaPublicKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, String.format("Failed to parse KMIP tag for TransparentDsaPublicKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class,
                    String.format("Expected object with %s tag for TransparentDsaPublicKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, String.format("Missing or non-text 'type' field for TransparentDsaPublicKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "TransparentDsaPublicKey 'value' must be a non-empty array");
            return null;
        }

        TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder = TransparentDsaPublicKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentDsaPublicKey transparentDsaPublicKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentDsaPublicKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentDsaPublicKey is not supported for KMIP spec %s", spec));
        }

        return transparentDsaPublicKey;
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
    private void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.G -> builder.g(p.getCodec().treeToValue(node, G.class));
            case KmipTag.Standard.Y -> builder.y(p.getCodec().treeToValue(node, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}