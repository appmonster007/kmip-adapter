package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for KeyValueLocationType.
 */
public class KeyValueLocationTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyValueLocationType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_VALUE_LOCATION_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public KeyValueLocationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "JSON node cannot be null for KeyValueLocationType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyValueLocationType.class, "Invalid KMIP tag for KeyValueLocationType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, String.format("Failed to parse KMIP tag for KeyValueLocationType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyValueLocationType.class,
                    String.format("Expected object with %s tag for KeyValueLocationType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "Missing or non-text 'type' field for KeyValueLocationType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        KeyValueLocationType.Value keyvaluelocationtypeValue;
        try {
            keyvaluelocationtypeValue = KeyValueLocationType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(KeyValueLocationType.class,
                    String.format("Unknown KeyValueLocationType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        KeyValueLocationType keyvaluelocationtype = new KeyValueLocationType(keyvaluelocationtypeValue);

        // Final validation: Ensure constructed KeyValueLocationType is supported
        if (!keyvaluelocationtype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("KeyValueLocationType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return keyvaluelocationtype;
    }
}
