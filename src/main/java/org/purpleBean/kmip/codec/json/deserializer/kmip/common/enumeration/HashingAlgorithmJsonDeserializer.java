package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for HashingAlgorithm.
 */
public class HashingAlgorithmJsonDeserializer extends KmipDataTypeJsonDeserializer<HashingAlgorithm> {
    private final KmipTag kmipTag = HashingAlgorithm.kmipTag;
    private final EncodingType encodingType = HashingAlgorithm.encodingType;

    @Override
    public HashingAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "JSON node cannot be null for HashingAlgorithm deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(HashingAlgorithm.class, "Invalid KMIP tag for HashingAlgorithm");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, String.format("Failed to parse KMIP tag for HashingAlgorithm: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(HashingAlgorithm.class,
                    String.format("Expected object with %s tag for HashingAlgorithm, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Missing or non-text 'type' field for HashingAlgorithm");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        HashingAlgorithm.Value hashingalgorithmValue;
        try {
            hashingalgorithmValue = HashingAlgorithm.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(HashingAlgorithm.class,
                    String.format("Unknown HashingAlgorithm value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        HashingAlgorithm hashingalgorithm = new HashingAlgorithm(hashingalgorithmValue);

        // Final validation: Ensure constructed HashingAlgorithm is supported
        if (!hashingalgorithm.isSupported()) {
            throw new NoSuchElementException(
                    String.format("HashingAlgorithm '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return hashingalgorithm;
    }
}
