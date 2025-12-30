package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.util.NoSuchElementException;

public class AttributeValueJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeValue.Value> {

    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue.Value deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeValue.class, "JSON node cannot be null for AttributeValue deserialization");
            return null;
        }

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

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeValue.class,
                    String.format("Expected object with %s tag for AttributeValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
        ) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing or non-text 'type' field for AttributeValue");
            return null;
        }
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).get();

        KmipSpec spec = KmipContext.getSpec();
        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(kmipTag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        AttributeValue.Value attributeValue = (AttributeValue.Value) p.getCodec().treeToValue(node, clazz);

        if (!attributeValue.isSupported()) {
            throw new NoSuchElementException(String.format("AttributeValue is not supported for KMIP spec %s", spec));
        }

        return attributeValue;
    }
}
