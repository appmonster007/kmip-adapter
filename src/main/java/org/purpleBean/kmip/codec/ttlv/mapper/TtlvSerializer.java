package org.purpleBean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface TtlvSerializer<T> {
    ByteBuffer serialize(T value, TtlvMapper mapper) throws IOException;
}
