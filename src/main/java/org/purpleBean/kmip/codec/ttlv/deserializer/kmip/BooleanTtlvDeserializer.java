package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

public class BooleanTtlvDeserializer implements TtlvDeserializer<Boolean> {
    private final EncodingType type = EncodingType.BOOLEAN;

    @Override
    public Boolean deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        Objects.requireNonNull(ttlvBuffer);
        if (ttlvBuffer.remaining() != type.getRawByteSize()) {
            throw new IllegalArgumentException(String.format("Expected %s bytes to get value", type.getRawByteSize()));
        }
        return ttlvBuffer.getLong() == 1L;
    }
}
