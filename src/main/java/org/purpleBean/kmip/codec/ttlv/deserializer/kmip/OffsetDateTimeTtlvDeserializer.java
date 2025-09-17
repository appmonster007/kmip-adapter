package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class OffsetDateTimeTtlvDeserializer extends TtlvDeserializer<OffsetDateTime> {
    private final EncodingType type = EncodingType.DATE_TIME;

    @Override
    public OffsetDateTime deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        Objects.requireNonNull(ttlvBuffer);
        if (ttlvBuffer.remaining() != type.getRawByteSize()) {
            throw new IllegalArgumentException(String.format("Expected %s bytes to get value", type.getRawByteSize()));
        }

        long seconds = ttlvBuffer.getLong();
        return Instant.ofEpochSecond(seconds).atOffset(ZoneOffset.UTC);
    }
}
