package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyValuePresent;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for KeyValuePresent.
 */
public class KeyValuePresentJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyValuePresent> {
    private final KmipTag kmipTag = KeyValuePresent.kmipTag;
    private final EncodingType encodingType = KeyValuePresent.encodingType;

    @Override
    public KeyValuePresent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "JSON node cannot be null for KeyValuePresent deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyValuePresent.class, "Invalid KMIP tag for KeyValuePresent");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyValuePresent.class, String.format("Failed to parse KMIP tag for KeyValuePresent: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyValuePresent.class,
                    String.format("Expected object with %s tag for KeyValuePresent, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyValuePresent.class, "Missing or non-text 'type' field for KeyValuePresent");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValuePresent.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String value = valueNode.asText();
        if (value == null || value.trim().isEmpty()) {
            ctxt.reportInputMismatch(KeyValuePresent.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        KeyValuePresent attribute = KeyValuePresent.of(Boolean.valueOf(value));

        // Final validation: Ensure constructed KeyValuePresent is supported
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("KeyValuePresent '%s' is not supported for KMIP spec %s", value, spec)
            );
        }

        return attribute;
    }
}
