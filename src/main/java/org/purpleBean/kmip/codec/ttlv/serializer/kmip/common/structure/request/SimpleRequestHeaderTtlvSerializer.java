package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.request;

import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class SimpleRequestHeaderTtlvSerializer implements TtlvSerializer<SimpleRequestHeader> {
    @Override
    public ByteBuffer serialize(SimpleRequestHeader value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(SimpleRequestHeader value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();

        byte type = value.getEncodingType().getTypeValue();

        byte[] payload = mapper.writeValueAsBytes(value.getProtocolVersion());

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
