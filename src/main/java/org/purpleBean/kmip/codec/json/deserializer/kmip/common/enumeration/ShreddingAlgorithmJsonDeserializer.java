package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ShreddingAlgorithm.
 */
public class ShreddingAlgorithmJsonDeserializer extends KmipDataTypeJsonDeserializer<ShreddingAlgorithm> {
    private final KmipTag kmipTag = ShreddingAlgorithm.kmipTag;
    private final EncodingType encodingType = ShreddingAlgorithm.encodingType;

    @Override
    public ShreddingAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "JSON node cannot be null for ShreddingAlgorithm deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Invalid KMIP tag for ShreddingAlgorithm");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, String.format("Failed to parse KMIP tag for ShreddingAlgorithm: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class,
                    String.format("Expected object with %s tag for ShreddingAlgorithm, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, "Missing or non-text 'type' field for ShreddingAlgorithm");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ShreddingAlgorithm.Value shreddingalgorithmValue;
        try {
            shreddingalgorithmValue = ShreddingAlgorithm.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ShreddingAlgorithm.class,
                    String.format("Unknown ShreddingAlgorithm value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ShreddingAlgorithm shreddingalgorithm = new ShreddingAlgorithm(shreddingalgorithmValue);

        // Final validation: Ensure constructed ShreddingAlgorithm is supported
        if (!shreddingalgorithm.isSupported()) {
            throw new NoSuchElementException(
                    String.format("ShreddingAlgorithm '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return shreddingalgorithm;
    }
}
