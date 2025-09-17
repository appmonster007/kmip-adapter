package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BooleanTtlvSerializer extends TtlvSerializer<Boolean> {
    private final EncodingType type = EncodingType.BOOLEAN;

    @Override
    public ByteBuffer serialize(Boolean value, TtlvMapper mapper) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(type.getRawByteSize());
        buffer.putLong(value ? 1L : 0L);
        return buffer;
    }
}
