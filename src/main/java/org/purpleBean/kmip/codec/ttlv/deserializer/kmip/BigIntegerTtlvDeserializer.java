package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Objects;

public class BigIntegerTtlvDeserializer extends TtlvDeserializer<BigInteger> {
    private final EncodingType type = EncodingType.BIG_INTEGER;

    @Override
    public BigInteger deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        Objects.requireNonNull(ttlvBuffer);
        if (!TtlvConstants.isProperlyPadded(ttlvBuffer.remaining())) {
            throw new IllegalArgumentException("Expected 8n bytes to get value");
        }

        return new BigInteger(ttlvBuffer.array());
    }
}
