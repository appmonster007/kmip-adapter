package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for RecommendedCurve.
 */
public class RecommendedCurveJsonDeserializer extends KmipDataTypeJsonDeserializer<RecommendedCurve> {
    private final KmipTag kmipTag = RecommendedCurve.kmipTag;
    private final EncodingType encodingType = RecommendedCurve.encodingType;

    @Override
    public RecommendedCurve deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "JSON node cannot be null for RecommendedCurve deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RecommendedCurve.class, "Invalid KMIP tag for RecommendedCurve");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RecommendedCurve.class, String.format("Failed to parse KMIP tag for RecommendedCurve: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RecommendedCurve.class,
                    String.format("Expected object with %s tag for RecommendedCurve, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "Missing or non-text 'type' field for RecommendedCurve");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RecommendedCurve.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(RecommendedCurve.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        RecommendedCurve.Value recommendedcurveValue;
        try {
            recommendedcurveValue = RecommendedCurve.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(RecommendedCurve.class,
                    String.format("Unknown RecommendedCurve value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        RecommendedCurve recommendedcurve = new RecommendedCurve(recommendedcurveValue);

        // Final validation: Ensure constructed RecommendedCurve is supported
        if (!recommendedcurve.isSupported()) {
            throw new NoSuchElementException(
                    String.format("RecommendedCurve '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return recommendedcurve;
    }
}
