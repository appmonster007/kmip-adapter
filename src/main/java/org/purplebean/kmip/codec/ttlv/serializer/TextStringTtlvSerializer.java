package org.purplebean.kmip.codec.ttlv.serializer;


import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

/**
 * TTLV serializer for {@link String}.
 */
public class TextStringTtlvSerializer extends TtlvSerializer<String> {
  private final EncodingType type = EncodingType.TEXT_STRING;

  @Override
  public ByteBuffer serialize(String value, TtlvMapper mapper) throws IOException {
    return ByteBuffer.wrap(value.getBytes());
  }
}
