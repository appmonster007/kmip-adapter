package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for KeyRoleType.
 */
public class KeyRoleTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyRoleType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_ROLE_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public KeyRoleType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyRoleType.class, "JSON node cannot be null for KeyRoleType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyRoleType.class, "Invalid KMIP tag for KeyRoleType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyRoleType.class, String.format("Failed to parse KMIP tag for KeyRoleType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyRoleType.class,
                    String.format("Expected object with %s tag for KeyRoleType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyRoleType.class, "Missing or non-text 'type' field for KeyRoleType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyRoleType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(KeyRoleType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        KeyRoleType.Value keyroletypeValue;
        try {
            keyroletypeValue = KeyRoleType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(KeyRoleType.class,
                    String.format("Unknown KeyRoleType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        KeyRoleType keyroletype = new KeyRoleType(keyroletypeValue);

        // Final validation: Ensure constructed KeyRoleType is supported
        if (!keyroletype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("KeyRoleType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return keyroletype;
    }
}
