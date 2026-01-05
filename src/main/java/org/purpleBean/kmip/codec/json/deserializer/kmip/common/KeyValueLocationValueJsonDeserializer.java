package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

import java.io.IOException;

public class KeyValueLocationValueJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyValueLocationValue> {
    private final KmipTag kmipTag = KeyValueLocationValue.kmipTag;
    private final EncodingType encodingType = KeyValueLocationValue.encodingType;

    @Override
    public KeyValueLocationValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, String.format("JSON node cannot be null for KeyValueLocationValue deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyValueLocationValue.class, String.format("Invalid KMIP tag for KeyValueLocationValue"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, String.format("Failed to parse KMIP tag for KeyValueLocationValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class,
                    String.format("Expected object with %s tag for KeyValueLocationValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, String.format("Missing or non-text 'type' field for KeyValueLocationValue"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "KeyValueLocationValue 'value' must be a non-empty array");
            return null;
        }

        KeyValueLocationValue keyValueLocationValue = KeyValueLocationValue.builder().value(valueNode.asText()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!keyValueLocationValue.isSupported()) {
            ctxt.reportInputMismatch(KeyValueLocationValue.class, "KeyValueLocationValue not supported for spec " + spec);
            return null;
        }

        return keyValueLocationValue;
    }
}