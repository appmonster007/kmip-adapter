package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractKmipStructureTtlvDeserializer<T extends KmipDataType, B> extends KmipDataTypeTtlvDeserializer<T> {

    private final KmipTag kmipTag;

    protected AbstractKmipStructureTtlvDeserializer(KmipTag kmipTag) {
        this.kmipTag = kmipTag;
    }

    @Override
    public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != getEncodingType().getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", getEncodingType().getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        B builder = createBuilder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject.toByteBuffer(), mapper);
        }

        T result = build(builder);

        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
        return result;
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException;

    protected abstract T build(B builder);

    protected abstract EncodingType getEncodingType();
}