package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Fresh;

import java.io.IOException;
import java.nio.ByteBuffer;

public class FreshTtlvSerializer extends KmipDataTypeTtlvSerializer<Fresh> {

    @Override
    public ByteBuffer serialize(Fresh fresh, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(fresh, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(Fresh fresh, TtlvMapper mapper) throws IOException {
        if (fresh == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!fresh.isSupported()) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            fresh.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = fresh.getKmipTag().getTagBytes();
        byte type = fresh.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(fresh.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}