package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for Fips186Variation.
 */
public class Fips186VariationJsonDeserializer extends KmipDataTypeJsonDeserializer<Fips186Variation> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FIPS186_VARIATION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public Fips186Variation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Fips186Variation.class, "JSON node cannot be null for Fips186Variation deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Fips186Variation.class, "Invalid KMIP tag for Fips186Variation");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Fips186Variation.class, String.format("Failed to parse KMIP tag for Fips186Variation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(Fips186Variation.class,
                    String.format("Expected object with %s tag for Fips186Variation, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Missing or non-text 'type' field for Fips186Variation");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Fips186Variation.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(Fips186Variation.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        Fips186Variation.Value fips186variationValue;
        try {
            fips186variationValue = Fips186Variation.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(Fips186Variation.class,
                    String.format("Unknown Fips186Variation value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        Fips186Variation fips186variation = new Fips186Variation(fips186variationValue);

        // Final validation: Ensure constructed Fips186Variation is supported
        if (!fips186variation.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("Fips186Variation '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return fips186variation;
    }
}
