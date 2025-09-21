package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ValidityIndicator.
 */
public class ValidityIndicatorJsonDeserializer extends KmipDataTypeJsonDeserializer<ValidityIndicator> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.VALIDITY_INDICATOR);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ValidityIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ValidityIndicator.class, "JSON node cannot be null for ValidityIndicator deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ValidityIndicator.class, "Invalid KMIP tag for ValidityIndicator");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ValidityIndicator.class, String.format("Failed to parse KMIP tag for ValidityIndicator: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(ValidityIndicator.class,
                    String.format("Expected object with %s tag for ValidityIndicator, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ValidityIndicator.class, "Missing or non-text 'type' field for ValidityIndicator");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidityIndicator.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ValidityIndicator.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ValidityIndicator.Value validityindicatorValue;
        try {
            validityindicatorValue = ValidityIndicator.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ValidityIndicator.class,
                    String.format("Unknown ValidityIndicator value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ValidityIndicator validityindicator = new ValidityIndicator(validityindicatorValue);

        // Final validation: Ensure constructed ValidityIndicator is supported
        if (!validityindicator.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ValidityIndicator '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return validityindicator;
    }
}
