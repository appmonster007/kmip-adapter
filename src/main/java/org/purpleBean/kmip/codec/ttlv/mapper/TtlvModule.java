package org.purpleBean.kmip.codec.ttlv.mapper;

import java.util.HashMap;
import java.util.Map;

public class TtlvModule {

    private final Map<Class<?>, TtlvSerializer<?>> serializers = new HashMap<>();
    private final Map<Class<?>, TtlvDeserializer<?>> deserializers = new HashMap<>();

    public <T> TtlvModule addSerializer(Class<T> type, TtlvSerializer<T> serializer) {
        serializers.put(type, serializer);
        return this;
    }

    public <T> TtlvModule addDeserializer(Class<T> type, TtlvDeserializer<T> deserializer) {
        deserializers.put(type, deserializer);
        return this;
    }

    Map<Class<?>, TtlvSerializer<?>> getSerializers() {
        return serializers;
    }

    Map<Class<?>, TtlvDeserializer<?>> getDeserializers() {
        return deserializers;
    }
}
