package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyMaterial;
import org.purpleBean.kmip.common.structure.KeyMaterialStructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class KeyMaterialStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyMaterialStructure> {
    private final KmipTag kmipTag = KeyMaterialStructure.kmipTag;
    private final EncodingType encodingType = KeyMaterialStructure.encodingType;

    @Override
    public KeyMaterialStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class, String.format("JSON node cannot be null for KeyMaterialStructure deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyMaterialStructure.class, String.format("Invalid KMIP tag for KeyMaterialStructure"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class, String.format("Failed to parse KMIP tag for KeyMaterialStructure: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || !tag.getValue().equals(kmipTag.getValue())) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class,
                    String.format("Expected object with %s tag for KeyMaterialStructure, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class, String.format("Missing or non-text 'type' field for KeyMaterialStructure"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isArray() || valueNode.isEmpty()) {
            ctxt.reportInputMismatch(KeyMaterialStructure.class, "KeyMaterialStructure 'value' must be a non-empty array");
            return null;
        }

        List<KmipDataType> values = new ArrayList<>();
        for (JsonNode childNode : valueNode) {
            values.add(deserializeObjects(childNode, p, ctxt));
        }

        KeyMaterialStructure keyMaterialStructure = KeyMaterialStructure.of(values);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!keyMaterialStructure.isSupported()) {
            throw new NoSuchElementException(String.format("KeyMaterialStructure is not supported for KMIP spec %s", spec));
        }

        return keyMaterialStructure;
    }

    private KmipDataType deserializeObjects(JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!node.has("tag") || !node.has("type") || !node.has("value")) {
            ctxt.reportInputMismatch(KeyMaterial.class, "Missing 'tag', 'type', or 'value' field in JSON");
            return null;
        }

        if (!node.has("tag") && !node.get("tag").isTextual()) {
            ctxt.reportInputMismatch(KeyMaterial.class, "Invalid 'tag' field in JSON");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyMaterial.class, "Invalid KMIP tag for KeyMaterial");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyMaterial.class, String.format("Failed to parse KMIP tag for KeyMaterial: %s", e.getMessage()));
            return null;
        }

        if (!node.has("type") && !node.get("type").isTextual()) {
            ctxt.reportInputMismatch(KeyMaterial.class, "Invalid 'type' field in JSON");
            return null;
        }
        String type = node.get("type").asText();
        EncodingType encodingType = EncodingType.fromName(type).get();

        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(tag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", tag.getValue(), encodingType));
        }

        return p.getCodec().treeToValue(node, clazz);
    }

}