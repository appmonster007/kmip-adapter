package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for KeyFormatType.
 */
public class KeyFormatTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyFormatType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_FORMAT_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public KeyFormatType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyFormatType.class, "JSON node cannot be null for KeyFormatType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyFormatType.class, "Invalid KMIP tag for KeyFormatType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyFormatType.class, String.format("Failed to parse KMIP tag for KeyFormatType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyFormatType.class,
                    String.format("Expected object with %s tag for KeyFormatType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyFormatType.class, "Missing or non-text 'type' field for KeyFormatType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyFormatType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(KeyFormatType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        KeyFormatType.Value keyformattypeValue;
        try {
            keyformattypeValue = KeyFormatType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(KeyFormatType.class,
                    String.format("Unknown KeyFormatType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        KeyFormatType keyformattype = new KeyFormatType(keyformattypeValue);

        // Final validation: Ensure constructed KeyFormatType is supported
        if (!keyformattype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("KeyFormatType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return keyformattype;
    }
}
