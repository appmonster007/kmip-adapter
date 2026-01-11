package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.purpleBean.kmip.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;

public class KmipDataTypeJsonDeserializer<T extends KmipDataType> extends JsonDeserializer<KmipDataType> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TokenBuffer buffer = new TokenBuffer(p, ctxt);
        buffer.copyCurrentStructure(p);

        JsonNode rootNode = buffer.asParser().getCodec().readTree(buffer.asParser());
        JsonNode tagNode = rootNode.get("tag");
        JsonNode typeNode = rootNode.get("type");

        if (tagNode == null || typeNode == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        KmipTag.Value kmipTagValue = KmipTag.fromName(spec, tagNode.asText());
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).orElse(null);

        if (kmipTagValue == null || encodingType == null) {
            return null;
        }

        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(kmipTagValue, encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTagValue.getValue(), encodingType));
        }

        return (T) ctxt.readValue(buffer.asParser(), clazz);
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