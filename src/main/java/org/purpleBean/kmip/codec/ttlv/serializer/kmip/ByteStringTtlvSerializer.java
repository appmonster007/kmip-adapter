package org.purpleBean.kmip.codec.ttlv.serializer.kmip;


import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteStringTtlvSerializer implements TtlvSerializer<ByteBuffer> {
    private final EncodingType type = EncodingType.BYTE_STRING;

    @Override
    public ByteBuffer serialize(ByteBuffer value, TtlvMapper mapper) throws IOException {
        return value;
    }
}
