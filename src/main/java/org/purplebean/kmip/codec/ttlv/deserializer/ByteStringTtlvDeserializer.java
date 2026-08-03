package org.purplebean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * TTLV deserializer for {@link ByteBuffer}.
 */
public class ByteStringTtlvDeserializer extends TtlvDeserializer<ByteBuffer> {
  private final EncodingType type = EncodingType.BYTE_STRING;

  @Override
  public ByteBuffer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return ttlvBuffer;
  }
}
