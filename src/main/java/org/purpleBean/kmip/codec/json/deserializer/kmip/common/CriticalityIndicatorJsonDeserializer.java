package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for CriticalityIndicator.
 */
public class CriticalityIndicatorJsonDeserializer extends KmipDataTypeJsonDeserializer<CriticalityIndicator> {
    private final KmipTag kmipTag = CriticalityIndicator.kmipTag;
    private final EncodingType encodingType = CriticalityIndicator.encodingType;

    @Override
    public CriticalityIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "JSON node cannot be null for CriticalityIndicator deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CriticalityIndicator.class, "Invalid KMIP tag for CriticalityIndicator");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("Failed to parse KMIP tag for CriticalityIndicator: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class,
                    String.format("Expected object with %s tag for CriticalityIndicator, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "Missing or non-text 'type' field for CriticalityIndicator");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String value = valueNode.asText();
        if (value == null || value.trim().isEmpty()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        CriticalityIndicator attribute = CriticalityIndicator.of(Boolean.valueOf(value));

        // Final validation: Ensure constructed CriticalityIndicator is supported
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("CriticalityIndicator '%s' is not supported for KMIP spec %s", value, spec)
            );
        }

        return attribute;
    }
}
