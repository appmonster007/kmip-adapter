package org.purpleBean.kmip.codec.ttlv.serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

public class LongTtlvSerializer extends TtlvSerializer<Long> {
  private final EncodingType type = EncodingType.LONG_INTEGER;

  @Override
  public ByteBuffer serialize(Long value, TtlvMapper mapper) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(type.getRawByteSize());
    buffer.putLong(value);
    return buffer;
  }
}
