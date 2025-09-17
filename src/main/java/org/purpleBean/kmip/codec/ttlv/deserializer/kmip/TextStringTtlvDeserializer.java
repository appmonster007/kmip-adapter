package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TextStringTtlvDeserializer extends TtlvDeserializer<String> {
    private final EncodingType type = EncodingType.TEXT_STRING;

    @Override
    public String deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return new String(ttlvBuffer.array());
    }
}
