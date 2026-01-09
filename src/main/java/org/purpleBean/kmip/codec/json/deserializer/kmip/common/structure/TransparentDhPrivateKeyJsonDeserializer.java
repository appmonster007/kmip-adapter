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
import org.purpleBean.kmip.common.structure.TransparentDhPrivateKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentDhPrivateKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentDhPrivateKey> {
    private final KmipTag kmipTag = TransparentDhPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentDhPrivateKey.encodingType;

    @Override
    public TransparentDhPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, String.format("JSON node cannot be null for TransparentDhPrivateKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentDhPrivateKey.class, String.format("Invalid KMIP tag for TransparentDhPrivateKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, String.format("Failed to parse KMIP tag for TransparentDhPrivateKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class,
                    String.format("Expected object with %s tag for TransparentDhPrivateKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, String.format("Missing or non-text 'type' field for TransparentDhPrivateKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, "TransparentDhPrivateKey 'value' must be a non-empty array");
            return null;
        }

        TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder = TransparentDhPrivateKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentDhPrivateKey transparentDhPrivateKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentDhPrivateKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentDhPrivateKey is not supported for KMIP spec %s", spec));
        }

        return transparentDhPrivateKey;
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
    private void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.G -> builder.g(p.getCodec().treeToValue(node, G.class));
            case KmipTag.Standard.J -> builder.j(p.getCodec().treeToValue(node, J.class));
            case KmipTag.Standard.X -> builder.x(p.getCodec().treeToValue(node, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}