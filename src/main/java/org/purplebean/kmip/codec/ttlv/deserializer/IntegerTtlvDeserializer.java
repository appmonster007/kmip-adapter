package org.purplebean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * TTLV deserializer for {@link Integer}.
 */
public class IntegerTtlvDeserializer extends TtlvDeserializer<Integer> {
  private final EncodingType type = EncodingType.INTEGER;

  @Override
  public Integer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    Objects.requireNonNull(ttlvBuffer);
    if (ttlvBuffer.remaining() != type.getRawByteSize()) {
      throw new IllegalArgumentException(
          String.format("Expected %s bytes to get value", type.getRawByteSize()));
    }
    return ttlvBuffer.getInt();
  }
}
