package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ActivationDateAttributeTtlvSerializer implements TtlvSerializer<ActivationDateAttribute> {
    @Override
    public ByteBuffer serialize(ActivationDateAttribute value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(ActivationDateAttribute value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = EncodingType.DATE_TIME.getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getDateTime()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}

