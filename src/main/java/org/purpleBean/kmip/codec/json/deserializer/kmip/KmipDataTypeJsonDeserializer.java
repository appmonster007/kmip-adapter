package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipTag;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Optional;

public class KmipDataTypeJsonDeserializer<T extends KmipDataType> extends JsonDeserializer<KmipDataType> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = ctxt.readTree(p);

        JsonNode tagNode = node.get("tag");
        JsonNode typeNode = node.get("type");

        if (tagNode == null || !tagNode.isTextual() || typeNode == null || !typeNode.isTextual()) {
            ctxt.handleUnexpectedToken(KmipDataType.class, p);
            return null;
        }

        KmipTag.Value kmipTagValue = KmipTag.fromName(tagNode.asText());
        Optional<EncodingType> encodingType = EncodingType.fromName(typeNode.asText());

        if (encodingType.isEmpty()) {
            ctxt.handleUnexpectedToken(KmipDataType.class, p);
            return null;
        }

        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(kmipTagValue, encodingType.get());
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTagValue.getValue(), encodingType.get()));
        }

        return (T) ctxt.readTreeAsValue(node, clazz);
    }

    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType) {
        return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
    }

    @Override
    public Class<?> handledType() {
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