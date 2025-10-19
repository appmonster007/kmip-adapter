package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ArchiveDateTtlvSerializer extends KmipDataTypeTtlvSerializer<ArchiveDate> {
    @Override
    public ByteBuffer serialize(ArchiveDate value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(ArchiveDate value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            value.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
