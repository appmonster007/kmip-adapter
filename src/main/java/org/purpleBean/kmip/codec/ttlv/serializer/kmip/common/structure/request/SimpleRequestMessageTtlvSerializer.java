package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure.request;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SimpleRequestMessageTtlvSerializer extends KmipDataTypeTtlvSerializer<SimpleRequestMessage> {
    @Override
    public ByteBuffer serialize(SimpleRequestMessage value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(SimpleRequestMessage value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
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
