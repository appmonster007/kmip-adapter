package org.purpleBean.kmip.codec.ttlv.mapper;

import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class TtlvMapper {
    private final Map<Class<?>, TtlvSerializer<?>> serializers = new ConcurrentHashMap<>();
    private final Map<Class<?>, TtlvDeserializer<?>> deserializers = new ConcurrentHashMap<>();

    public void registerModule(TtlvModule module) {
        Objects.requireNonNull(module, "module cannot be null");
        serializers.putAll(module.getSerializers());
        deserializers.putAll(module.getDeserializers());
    }


    // Obtain a ByteBuffer containing the full TTLV encoding for the POJO
    public <T> ByteBuffer writeValueAsByteBuffer(T value) throws IOException {
        Objects.requireNonNull(value, "value cannot be null");
        TtlvSerializer<T> ser = getSerializer(value.getClass());
        ByteBuffer buf = ser.serialize(value, this);
        // Ensure buffer is positioned for reading
        buf.rewind();
        return buf;
    }


    // Deserialize from a ByteBuffer containing a single TTLV element
    public <T> T readValue(ByteBuffer buffer, Class<T> clazz) throws IOException {
        Objects.requireNonNull(buffer, "buffer cannot be null");
        Objects.requireNonNull(clazz, "clazz cannot be null");
        TtlvDeserializer<T> deser = getDeserializer(clazz);
        return deser.deserialize(buffer, this);
    }


    // Convenience high-level API similar to ObjectMapper
    public <T> byte[] writeValueAsBytes(T value) throws IOException {
        ByteBuffer bb = writeValueAsByteBuffer(value);
        byte[] out = new byte[bb.remaining()];
        bb.get(out);
        return out;
    }


    public <T> T readValue(byte[] data, Class<T> clazz) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(data).order(TtlvConstants.BYTE_ORDER);
        return readValue(buffer, clazz);
    }


    @SuppressWarnings("unchecked")
    private <T> TtlvSerializer<T> getSerializer(Class<?> type) {
        // First try the exact type
        TtlvSerializer<?> serializer = serializers.get(type);
        if (serializer != null) {
            return (TtlvSerializer<T>) serializer;
        }

        // Then try superclasses
        Class<?> current = type.getSuperclass();
        while (current != null && current != Object.class) {
            serializer = serializers.get(current);
            if (serializer != null) {
                return (TtlvSerializer<T>) serializer;
            }
            current = current.getSuperclass();
        }

        // Then try interfaces
        for (Class<?> iface : type.getInterfaces()) {
            serializer = serializers.get(iface);
            if (serializer != null) {
                return (TtlvSerializer<T>) serializer;
            }
        }

        throw new IllegalArgumentException("No serializer found for type: " + type.getName());
    }

    @SuppressWarnings("unchecked")
    private <T> TtlvDeserializer<T> getDeserializer(Class<T> type) {
        // First try the exact type
        TtlvDeserializer<?> deserializer = deserializers.get(type);
        if (deserializer != null) {
            return (TtlvDeserializer<T>) deserializer;
        }

        // Then try superclasses
        Class<?> current = type.getSuperclass();
        while (current != null && current != Object.class) {
            deserializer = deserializers.get(current);
            if (deserializer != null) {
                return (TtlvDeserializer<T>) deserializer;
            }
            current = current.getSuperclass();
        }

        // Then try interfaces
        for (Class<?> iface : type.getInterfaces()) {
            deserializer = deserializers.get(iface);
            if (deserializer != null) {
                return (TtlvDeserializer<T>) deserializer;
            }
        }

        throw new IllegalArgumentException("No deserializer found for type: " + type.getName());
    }
}