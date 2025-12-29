package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

import java.io.IOException;

public class KeyPartIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyPartIdentifier> {
    private final KmipTag kmipTag = KeyPartIdentifier.kmipTag;
    private final EncodingType encodingType = KeyPartIdentifier.encodingType;

    @Override
    public KeyPartIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, String.format("JSON node cannot be null for KeyPartIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyPartIdentifier.class, String.format("Invalid KMIP tag for KeyPartIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, String.format("Failed to parse KMIP tag for KeyPartIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class,
                    String.format("Expected object with %s tag for KeyPartIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, String.format("Missing or non-text 'type' field for KeyPartIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "KeyPartIdentifier 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        KeyPartIdentifier keyPartIdentifier = KeyPartIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!keyPartIdentifier.isSupported()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "KeyPartIdentifier not supported for spec " + spec);
            return null;
        }

        return keyPartIdentifier;
    }
}