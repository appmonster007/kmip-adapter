package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

public class IntegerTtlvDeserializer extends TtlvDeserializer<Integer> {
    private final EncodingType type = EncodingType.INTEGER;

    @Override
    public Integer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        Objects.requireNonNull(ttlvBuffer);
        if (ttlvBuffer.remaining() != type.getRawByteSize()) {
            throw new IllegalArgumentException(String.format("Expected %s bytes to get value", type.getRawByteSize()));
        }
        return ttlvBuffer.getInt();
    }
}
