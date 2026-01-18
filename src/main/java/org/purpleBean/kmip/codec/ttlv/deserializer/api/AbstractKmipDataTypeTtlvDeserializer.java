package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class AbstractKmipDataTypeTtlvDeserializer<T extends KmipDataType, V> extends KmipDataTypeTtlvDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;
    private final Class<V> valueClass;
    private final Function<V, T> factory;

    protected AbstractKmipDataTypeTtlvDeserializer(KmipTag kmipTag, EncodingType encodingType, Class<V> valueClass, Function<V, T> factory) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
        this.valueClass = valueClass;
        this.factory = factory;
    }

    @Override
    public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", encodingType.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        V value = mapper.readValue(bb, valueClass);
        T result = factory.apply(value);

        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
        return result;
    }
}