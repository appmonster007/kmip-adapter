package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteStringTtlvDeserializer extends TtlvDeserializer<ByteBuffer> {
    private final EncodingType type = EncodingType.BYTE_STRING;

    @Override
    public ByteBuffer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return ttlvBuffer;
    }
}
