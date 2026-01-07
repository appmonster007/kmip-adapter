package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.KeyMaterial;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialByteStringJsonDeserializer extends KmipDataTypeJsonDeserializer<KeyMaterial.ByteString> {
    private final KmipTag kmipTag = KeyMaterial.ByteString.kmipTag;
    private final EncodingType encodingType = KeyMaterial.ByteString.encodingType;

    @Override
    public KeyMaterial.ByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class, String.format("JSON node cannot be null for KeyMaterial.ByteString deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(KeyMaterial.ByteString.class, String.format("Invalid KMIP tag for KeyMaterial.ByteString"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class, String.format("Failed to parse KMIP tag for KeyMaterial.ByteString: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class,
                    String.format("Expected object with %s tag for KeyMaterial.ByteString, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class, String.format("Missing or non-text 'type' field for KeyMaterial.ByteString"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class, "KeyMaterial.ByteString 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        KeyMaterial.ByteString keyMaterialByteString = KeyMaterial.ByteString.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!keyMaterialByteString.isSupported()) {
            ctxt.reportInputMismatch(KeyMaterial.ByteString.class, "KeyMaterial.ByteString not supported for spec " + spec);
            return null;
        }

        return keyMaterialByteString;
    }
}