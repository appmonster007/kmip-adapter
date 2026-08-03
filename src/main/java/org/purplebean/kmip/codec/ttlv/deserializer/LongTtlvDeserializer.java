package org.purplebean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * TTLV deserializer for {@link Long}.
 */
public class LongTtlvDeserializer extends TtlvDeserializer<Long> {
  private final EncodingType type = EncodingType.LONG_INTEGER;

  @Override
  public Long deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    Objects.requireNonNull(ttlvBuffer);
    if (ttlvBuffer.remaining() != type.getRawByteSize()) {
      throw new IllegalArgumentException(
          String.format("Expected %s bytes to get value", type.getRawByteSize()));
    }
    return ttlvBuffer.getLong();
  }
}
