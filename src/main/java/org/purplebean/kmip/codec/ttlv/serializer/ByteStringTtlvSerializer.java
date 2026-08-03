package org.purplebean.kmip.codec.ttlv.serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

/**
 * TTLV serializer for {@link ByteBuffer}.
 */
public class ByteStringTtlvSerializer extends TtlvSerializer<ByteBuffer> {
  private final EncodingType type = EncodingType.BYTE_STRING;

  @Override
  public ByteBuffer serialize(ByteBuffer value, TtlvMapper mapper) throws IOException {
    return value;
  }
}
