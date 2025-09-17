package org.purpleBean.kmip.codec.ttlv.serializer.kmip;


import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TextStringTtlvSerializer extends TtlvSerializer<String> {
    private final EncodingType type = EncodingType.TEXT_STRING;

    @Override
    public ByteBuffer serialize(String value, TtlvMapper mapper) throws IOException {
        return ByteBuffer.wrap(value.getBytes());
    }
}
