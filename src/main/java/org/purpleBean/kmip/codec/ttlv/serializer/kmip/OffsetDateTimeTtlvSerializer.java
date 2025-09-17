package org.purpleBean.kmip.codec.ttlv.serializer.kmip;


import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class OffsetDateTimeTtlvSerializer extends TtlvSerializer<OffsetDateTime> {
    private final EncodingType type = EncodingType.DATE_TIME;

    @Override
    public ByteBuffer serialize(OffsetDateTime value, TtlvMapper mapper) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(type.getRawByteSize());
        bb.putLong(value.toEpochSecond());
        return bb;
    }
}
