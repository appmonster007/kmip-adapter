package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AttributeValueStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueStructure> {
    private final KmipTag kmipTag = AttributeValueStructure.kmipTag;
    private final EncodingType encodingType = AttributeValueStructure.encodingType;

    @Override
    public AttributeValueStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "JSON node cannot be null for AttributeValue.Structure deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueStructure.class, "Invalid KMIP tag for AttributeValue.Structure");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, String.format("Failed to parse KMIP tag for AttributeValue.Structure: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueStructure.class,
                    String.format("Expected object with %s tag for AttributeValue.Structure, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "Missing or non-text 'type' field for AttributeValue.Structure");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isArray()) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "AttributeValue.Structure 'value' must be a non-empty array");
            return null;
        }

        List<KmipDataType> values = new ArrayList<>();
        for (JsonNode childNode : valueNode) {
            values.add(deserializeObjects(childNode, p, ctxt));
        }
        AttributeValueStructure attributeValueStructure = AttributeValueStructure.of(values);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueStructure.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "AttributeValue.Structure not supported for spec " + spec);
            return null;
        }

        return attributeValueStructure;
    }

    private KmipDataType deserializeObjects(JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!node.has("tag") || !node.has("type") || !node.has("value")) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing 'tag', 'type', or 'value' field in JSON");
            return null;
        }

        if (!node.has("tag") && !node.get("tag").isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid 'tag' field in JSON");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValue.class, "Invalid KMIP tag for AttributeValue");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValue.class, String.format("Failed to parse KMIP tag for AttributeValue: %s", e.getMessage()));
            return null;
        }

        if (!node.has("type") && !node.get("type").isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid 'type' field in JSON");
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
