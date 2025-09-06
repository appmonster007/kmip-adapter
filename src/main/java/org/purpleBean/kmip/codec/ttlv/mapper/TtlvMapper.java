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


    @SuppressWarnings("unchecked") // TODO : remove?
    private <T> TtlvSerializer<T> getSerializer(Class<?> type) {
        TtlvSerializer<?> serializer = serializers.get(type);
        if (serializer == null) {
            throw new IllegalStateException("No serializer registered for type: " + type.getName());
        }
        return (TtlvSerializer<T>) serializer;
    }

    @SuppressWarnings("unchecked") // TODO : remove?
    private <T> TtlvDeserializer<T> getDeserializer(Class<T> type) {
        TtlvDeserializer<?> deserializer = deserializers.get(type);
        if (deserializer == null) {
            throw new IllegalStateException("No deserializer registered for type: " + type.getName());
        }
        return (TtlvDeserializer<T>) deserializer;
    }
}