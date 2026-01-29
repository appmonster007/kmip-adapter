package org.purpleBean.kmip.codec.ttlv.serializer.api;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractKmipDataTypeTtlvSerializer<T extends KmipDataType> extends KmipDataTypeTtlvSerializer<T> {

    @Override
    public ByteBuffer serialize(T obj, TtlvMapper mapper) throws IOException {
        if (obj == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!obj.isSupported()) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            obj.getKmipTag().getDescription(), spec)
            );
        }

        var value = obj.getValue();
        byte[] payload;
        if (obj.getEncodingType() == EncodingType.STRUCTURE) {
            KmipDataType[] nestedValues = (KmipDataType[]) value;
            List<ByteBuffer> nestedObjects = new ArrayList<>();
            for (KmipDataType object : nestedValues) {
                if (object != null) {
                    nestedObjects.add(mapper.writeValueAsByteBuffer(object));
                }
            }

            int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
            ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
            nestedObjects.forEach(payloadBuffer::put);
            payload = payloadBuffer.array();
        } else if (obj.getEncodingType() == EncodingType.ENUMERATION) {
            payload = mapper.writeValueAsByteBuffer(((KmipEnumeration.Value<?>) value).getValue()).array();
        } else {
            payload = mapper.writeValueAsByteBuffer(value).array();
        }

        return TtlvObject.builder()
                .tag(obj.getKmipTag().getTagBytes())
                .type(obj.getEncodingType().getTypeValue())
                .value(payload)
                .build()
                .toByteBuffer();
    }
}