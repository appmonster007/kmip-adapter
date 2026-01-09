package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TransparentSymmetricKeyJsonDeserializer extends KmipDataTypeJsonDeserializer<TransparentSymmetricKey> {
    private final KmipTag kmipTag = TransparentSymmetricKey.kmipTag;
    private final EncodingType encodingType = TransparentSymmetricKey.encodingType;

    @Override
    public TransparentSymmetricKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, String.format("JSON node cannot be null for TransparentSymmetricKey deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(TransparentSymmetricKey.class, String.format("Invalid KMIP tag for TransparentSymmetricKey"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, String.format("Failed to parse KMIP tag for TransparentSymmetricKey: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class,
                    String.format("Expected object with %s tag for TransparentSymmetricKey, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, String.format("Missing or non-text 'type' field for TransparentSymmetricKey"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "TransparentSymmetricKey 'value' must be a non-empty array");
            return null;
        }

        TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder = TransparentSymmetricKey.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        TransparentSymmetricKey transparentSymmetricKey = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!transparentSymmetricKey.isSupported()) {
            throw new NoSuchElementException(String.format("TransparentSymmetricKey is not supported for KMIP spec %s", spec));
        }

        return transparentSymmetricKey;
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
    private void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY -> builder.key(p.getCodec().treeToValue(node, Key.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}