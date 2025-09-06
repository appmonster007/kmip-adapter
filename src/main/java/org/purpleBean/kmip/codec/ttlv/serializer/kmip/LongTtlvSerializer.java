package org.purpleBean.kmip.codec.ttlv.serializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LongTtlvSerializer implements TtlvSerializer<Long> {
    private final EncodingType type = EncodingType.INTEGER;

    @Override
    public ByteBuffer serialize(Long value, TtlvMapper mapper) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(type.getRawByteSize());
        buffer.putLong(value);
        return buffer;
    }
}
