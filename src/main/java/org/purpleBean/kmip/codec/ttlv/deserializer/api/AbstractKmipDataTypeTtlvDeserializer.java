package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractKmipDataTypeTtlvDeserializer<T extends KmipDataType, B> extends KmipDataTypeTtlvDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;

    protected AbstractKmipDataTypeTtlvDeserializer(KmipTag kmipTag, EncodingType encodingType) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
    }

    @Override
    public T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        B builder = createBuilder();

        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        byte[] tag = verifyTag(obj, mapper, builder);
        byte type = verifyType(obj, mapper, builder);

        if (EncodingType.STRUCTURE.getTypeValue() == type) {
            List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
            for (TtlvObject ttlvObject : nestedObjects) {
                setValue(builder, ttlvObject.getTag(), type, ttlvObject.toByteBuffer(), mapper);
            }
        } else {
            ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
            setValue(builder, null, type, bb, mapper);
        }

        T result = build(builder);

        verifyVersionSupport(result);
        return result;
    }

    protected void verifyVersionSupport(T result) {
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
    }

    protected byte[] verifyTag(TtlvObject obj, TtlvMapper mapper, B builder) {
        if (!Arrays.equals(obj.getTag(), kmipTag.getTagBytes())) {
            throw new IllegalArgumentException(String.format("Expected %s tag, got %s", kmipTag.getDescription(), obj.getType()));
        }
        return obj.getTag();
    }

    protected byte verifyType(TtlvObject obj, TtlvMapper mapper, B builder) {
        if (obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }
        return obj.getType();
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException;

    protected abstract T build(B builder);
}