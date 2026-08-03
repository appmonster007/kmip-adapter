package org.purplebean.kmip.codec.ttlv.serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

public class BooleanTtlvSerializer extends TtlvSerializer<Boolean> {
  private final EncodingType type = EncodingType.BOOLEAN;

  @Override
  public ByteBuffer serialize(Boolean value, TtlvMapper mapper) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(type.getRawByteSize());
    buffer.putLong(value ? 1L : 0L);
    return buffer;
  }
}
