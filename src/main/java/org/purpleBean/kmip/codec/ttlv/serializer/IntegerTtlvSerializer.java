package org.purplebean.kmip.codec.ttlv.serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

public class IntegerTtlvSerializer extends TtlvSerializer<Integer> {
  private final EncodingType type = EncodingType.INTEGER;

  @Override
  public ByteBuffer serialize(Integer value, TtlvMapper mapper) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(type.getRawByteSize());
    buffer.putInt(value);
    return buffer;
  }
}
