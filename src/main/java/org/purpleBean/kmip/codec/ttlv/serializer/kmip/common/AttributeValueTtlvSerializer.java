package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AttributeValueTtlvSerializer extends KmipDataTypeTtlvSerializer<AttributeValue> {
    @Override
    public ByteBuffer serialize(AttributeValue value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(AttributeValue attributeValue, TtlvMapper mapper) throws IOException {
        if (attributeValue == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupportedFor(spec)) {
            throw new IOException(
                    String.format("%s is not supported for KMIP spec %s",
                            attributeValue.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = attributeValue.getKmipTag().getTagBytes();
        byte type = attributeValue.getEncodingType().getTypeValue();
        byte[] payload;
        if (attributeValue.getEncodingType() == EncodingType.STRUCTURE) {
            List<KmipDataType> nestedValues = attributeValue.getValues();
            List<ByteBuffer> nestedObjects = new ArrayList<ByteBuffer>();
            for (KmipDataType object : nestedValues) {
                if (object != null) {
                    nestedObjects.add(mapper.writeValueAsByteBuffer(object));
                }
            }
            int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
            ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
            nestedObjects.forEach(payloadBuffer::put);
            payload = payloadBuffer.array();
        } else {
            payload = mapper.writeValueAsByteBuffer(attributeValue.getValue()).array();
        }
        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
