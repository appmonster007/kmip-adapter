package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for Ephemeral.
 */
public class EphemeralJsonDeserializer extends KmipDataTypeJsonDeserializer<Ephemeral> {
    private final KmipTag kmipTag = Ephemeral.kmipTag;
    private final EncodingType encodingType = Ephemeral.encodingType;

    @Override
    public Ephemeral deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Ephemeral.class, "JSON node cannot be null for Ephemeral deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Ephemeral.class, "Invalid KMIP tag for Ephemeral");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Ephemeral.class, String.format("Failed to parse KMIP tag for Ephemeral: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Ephemeral.class,
                    String.format("Expected object with %s tag for Ephemeral, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Ephemeral.class, "Missing or non-text 'type' field for Ephemeral");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Ephemeral.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(Ephemeral.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        Ephemeral.Value ephemeralValue;
        try {
            ephemeralValue = Ephemeral.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(Ephemeral.class,
                    String.format("Unknown Ephemeral value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        Ephemeral ephemeral = new Ephemeral(ephemeralValue);

        // Final validation: Ensure constructed Ephemeral is supported
        if (!ephemeral.isSupported()) {
            throw new NoSuchElementException(
                    String.format("Ephemeral '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return ephemeral;
    }
}
