package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyMaterialByteString> {
    private final KmipTag kmipTag = KeyMaterialByteString.kmipTag;
    private final EncodingType encodingType = KeyMaterialByteString.encodingType;

    @Override
    public KeyMaterialByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class, String.format("JSON node cannot be null for KeyMaterialByteString deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyMaterialByteString.class, String.format("Invalid KMIP tag for KeyMaterialByteString"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class, String.format("Failed to parse KMIP tag for KeyMaterialByteString: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class,
                    String.format("Expected object with %s tag for KeyMaterialByteString, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class, String.format("Missing or non-text 'type' field for KeyMaterialByteString"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class, "KeyMaterialByteString 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        KeyMaterialByteString keyMaterialByteString = KeyMaterialByteString.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!keyMaterialByteString.isSupported()) {
            ctxt.reportInputMismatch(KeyMaterialByteString.class, "KeyMaterialByteString not supported for spec " + spec);
            return null;
        }

        return keyMaterialByteString;
    }
}