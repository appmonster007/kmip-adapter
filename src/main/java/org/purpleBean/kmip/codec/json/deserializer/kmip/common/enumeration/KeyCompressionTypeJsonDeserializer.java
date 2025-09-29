package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for KeyCompressionType.
 */
public class KeyCompressionTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyCompressionType> {
    private final KmipTag kmipTag = KeyCompressionType.kmipTag;
    private final EncodingType encodingType = KeyCompressionType.encodingType;

    @Override
    public KeyCompressionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "JSON node cannot be null for KeyCompressionType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyCompressionType.class, "Invalid KMIP tag for KeyCompressionType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyCompressionType.class, String.format("Failed to parse KMIP tag for KeyCompressionType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyCompressionType.class,
                    String.format("Expected object with %s tag for KeyCompressionType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Missing or non-text 'type' field for KeyCompressionType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyCompressionType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(KeyCompressionType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        KeyCompressionType.Value keycompressiontypeValue;
        try {
            keycompressiontypeValue = KeyCompressionType.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(KeyCompressionType.class,
                    String.format("Unknown KeyCompressionType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        KeyCompressionType keycompressiontype = new KeyCompressionType(keycompressiontypeValue);

        // Final validation: Ensure constructed KeyCompressionType is supported
        if (!keycompressiontype.isSupported()) {
            throw new NoSuchElementException(
                    String.format("KeyCompressionType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return keycompressiontype;
    }
}
