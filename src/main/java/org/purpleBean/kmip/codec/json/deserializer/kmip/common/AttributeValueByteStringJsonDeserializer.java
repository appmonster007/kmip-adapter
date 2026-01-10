package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueByteStringJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValueByteString> {
    private final KmipTag kmipTag = AttributeValueByteString.kmipTag;
    private final EncodingType encodingType = AttributeValueByteString.encodingType;

    @Override
    public AttributeValueByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, String.format("JSON node cannot be null for AttributeValue.ByteString deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeValueByteString.class, String.format("Invalid KMIP tag for AttributeValue.ByteString"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, String.format("Failed to parse KMIP tag for AttributeValue.ByteString: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValueByteString.class,
                    String.format("Expected object with %s tag for AttributeValue.ByteString, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, String.format("Missing or non-text 'type' field for AttributeValue.ByteString"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, "AttributeValue.ByteString 'value' must be a non-empty textual value");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        AttributeValueByteString attributeValueByteString = AttributeValueByteString.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueByteString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, "AttributeValue.ByteString not supported for spec " + spec);
            return null;
        }

        return attributeValueByteString;
    }
}
