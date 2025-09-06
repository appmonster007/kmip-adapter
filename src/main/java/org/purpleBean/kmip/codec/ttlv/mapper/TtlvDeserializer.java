package org.purpleBean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface TtlvDeserializer<T> {
    T deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException;
}
