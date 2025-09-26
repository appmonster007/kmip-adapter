package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CustomAttributeTtlvSerializer extends KmipDataTypeTtlvSerializer<Attribute.CustomAttribute> {

    @Override
    public ByteBuffer serialize(Attribute.CustomAttribute value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(Attribute.CustomAttribute value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IOException(
                String.format("%s is not supported for KMIP spec %s",
                value.getKmipTag().getDescription(), spec)
            );
        }

        byte[] valueBytes = mapper.writeValueAsByteBuffer(value.getValue()).array();
        byte[] tag = new byte[] {0x00, 0x00, 0x00};

        return TtlvObject.builder()
                .tag(tag)
                .type(value.getEncodingType().getTypeValue())
                .value(valueBytes)
                .build();
    }
}
