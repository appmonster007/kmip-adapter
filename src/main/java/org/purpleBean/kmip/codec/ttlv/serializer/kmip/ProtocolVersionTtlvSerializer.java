package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ProtocolVersionTtlvSerializer implements TtlvSerializer<ProtocolVersion> {
    @Override
    public ByteBuffer serialize(ProtocolVersion value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(ProtocolVersion value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        List<KmipDataType> nestedValues = value.getValues();

        byte[] tag = value.getKmipTag().getTagBytes();

        byte type = value.getEncodingType().getTypeValue();

        List<ByteBuffer> nestedObjects = new ArrayList<>();
        for (KmipDataType object : nestedValues) {
            nestedObjects.add(mapper.writeValueAsByteBuffer(object));
        }

        int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
        nestedObjects.forEach(payloadBuffer::put);
        byte[] payload = payloadBuffer.array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
