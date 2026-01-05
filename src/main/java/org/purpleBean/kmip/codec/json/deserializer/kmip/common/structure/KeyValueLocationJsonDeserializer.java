package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

import java.io.IOException;
import java.util.NoSuchElementException;

public class KeyValueLocationJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyValueLocation> {
    private final KmipTag kmipTag = KeyValueLocation.kmipTag;
    private final EncodingType encodingType = KeyValueLocation.encodingType;

    @Override
    public KeyValueLocation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "JSON node cannot be null for KeyValueLocation deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyValueLocation.class, "Invalid KMIP tag for KeyValueLocation");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyValueLocation.class, String.format("Failed to parse KMIP tag for KeyValueLocation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyValueLocation.class,
                    String.format("Expected object with %s tag for KeyValueLocation, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyValueLocation.class, String.format("Missing or non-text 'type' field for KeyValueLocation"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "KeyValueLocation 'value' must be a non-empty array");
            return null;
        }

        KeyValueLocation.KeyValueLocationBuilder builder = KeyValueLocation.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(KeyValueLocation.class, String.format("Failed to process field in KeyValueLocation: %s", e.getMessage()));
                return null;
            }
        }

        KeyValueLocation keyvaluelocation = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyvaluelocation.isSupported()) {
            throw new NoSuchElementException(String.format("KeyValueLocation is not supported for KMIP spec %s", spec));
        }

        return keyvaluelocation;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            KeyValueLocation.KeyValueLocationBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(p.getCodec().treeToValue(node, KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(p.getCodec().treeToValue(node, KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}