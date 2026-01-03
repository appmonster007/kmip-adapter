package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;

import java.io.IOException;
import java.util.NoSuchElementException;

public class DigestJsonDeserializer extends KmipDataTypeJsonDeserializer<Digest> {
    private final KmipTag kmipTag = Digest.kmipTag;
    private final EncodingType encodingType = Digest.encodingType;

    @Override
    public Digest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Digest.class, "JSON node cannot be null for Digest deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Digest.class, "Invalid KMIP tag for Digest");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Digest.class, String.format("Failed to parse KMIP tag for Digest: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Digest.class,
                    String.format("Expected object with %s tag for Digest, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Digest.class, String.format("Missing or non-text 'type' field for Digest"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(Digest.class, "Digest 'value' must be a non-empty array");
            return null;
        }

        Digest.DigestBuilder builder = Digest.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(Digest.class, String.format("Failed to process field in Digest: %s", e.getMessage()));
                return null;
            }
        }

        Digest digest = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!digest.isSupported()) {
            throw new NoSuchElementException(String.format("Digest is not supported for KMIP spec %s", spec));
        }

        return digest;
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
            Digest.DigestBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.HASHING_ALGORITHM -> builder.hashingAlgorithm(p.getCodec().treeToValue(node, HashingAlgorithm.class));
            case KmipTag.Standard.DIGEST_VALUE -> builder.digestValue(p.getCodec().treeToValue(node, DigestValue.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE -> builder.keyFormatType(p.getCodec().treeToValue(node, KeyFormatType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
