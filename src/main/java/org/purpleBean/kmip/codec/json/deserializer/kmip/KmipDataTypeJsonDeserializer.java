package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;

public class KmipDataTypeJsonDeserializer<T extends KmipDataType> extends JsonDeserializer<KmipDataType> {

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

        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
        ) {
            ctxt.reportInputMismatch(handledType(), String.format("Missing or non-text 'type' field for " + handledType().getSimpleName()));
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).orElse(null);

        if (encodingType == null) {
            return null;
        }

        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(tag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s for spec %s", tag.getValue(), encodingType, spec));
        }

        return (T) ctxt.readValue(p.getCodec().treeAsTokens(node), clazz);
    }

    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType) {
        return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
    }

    @Override
    public Class<?> handledType() {
        // Try to infer the generic parameter (T) from the concrete subclass declaration
        // Handles both raw classes and parameterized types (template classes)
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return c;
            }
            if (tArg instanceof ParameterizedType parameterized) {
                Type raw = parameterized.getRawType();
                if (raw instanceof Class<?> rc) {
                    return rc;
                }
            }
        }
        return super.handledType();
    }
}