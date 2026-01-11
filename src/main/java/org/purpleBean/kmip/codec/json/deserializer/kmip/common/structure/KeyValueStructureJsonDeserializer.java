package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.KeyValueStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class KeyValueStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyValueStructure> {
    private final KmipTag kmipTag = KeyValueStructure.kmipTag;
    private final EncodingType encodingType = KeyValueStructure.encodingType;

    @Override
    public KeyValueStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyValueStructure.class, String.format("JSON node cannot be null for KeyValueStructure deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyValueStructure.class, String.format("Invalid KMIP tag for KeyValueStructure"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyValueStructure.class, String.format("Failed to parse KMIP tag for KeyValueStructure: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyValueStructure.class,
                    String.format("Expected object with %s tag for KeyValueStructure, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyValueStructure.class, String.format("Missing or non-text 'type' field for KeyValueStructure"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(KeyValueStructure.class, "KeyValueStructure 'value' must be a non-empty array");
            return null;
        }

        KeyValueStructure.KeyValueStructureBuilder builder = KeyValueStructure.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        KeyValueStructure keyValueStructure = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyValueStructure.isSupported()) {
            throw new NoSuchElementException(String.format("KeyValueStructure is not supported for KMIP spec %s", spec));
        }

        return keyValueStructure;
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
    private void setValue(KeyValueStructure.KeyValueStructureBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_MATERIAL ->
                    builder.keyMaterial(p.getCodec().treeToValue(node, KeyMaterial.class));
            default -> builder.attribute(p.getCodec().treeToValue(node, KmipAttribute.class));
        }
    }
}