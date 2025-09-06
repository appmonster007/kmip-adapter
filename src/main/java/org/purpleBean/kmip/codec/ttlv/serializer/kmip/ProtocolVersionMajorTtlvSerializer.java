package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ProtocolVersionMajorTtlvSerializer implements TtlvSerializer<ProtocolVersion.ProtocolVersionMajor> {
    @Override
    public ByteBuffer serialize(ProtocolVersion.ProtocolVersionMajor value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(ProtocolVersion.ProtocolVersionMajor value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = EncodingType.INTEGER.getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
