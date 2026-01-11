package org.purpleBean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;

import java.io.IOException;
import java.util.function.Function;

public abstract class AbstractKmipJsonDeserializer<T extends KmipDataType, V> extends KmipDataTypeJsonDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;
    private final Class<V> valueClass;
    private final Function<V, T> factory;

    protected AbstractKmipJsonDeserializer(KmipTag kmipTag, EncodingType encodingType, Class<V> valueClass, Function<V, T> factory) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
        this.valueClass = valueClass;
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(handledType(), "JSON node cannot be null");
            return null;
        }

        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(handledType(), "Invalid KMIP tag");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(handledType(), "Failed to parse KMIP tag: " + e.getMessage());
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(handledType(), "Expected object with tag " + kmipTag.getValue().getValue());
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
            return null;
        }

        V value = p.getCodec().treeToValue(valueNode, valueClass);
        return factory.apply(value);
    }
}