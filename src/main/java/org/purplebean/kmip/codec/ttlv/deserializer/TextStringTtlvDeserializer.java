package org.purplebean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * TTLV deserializer for {@link String}.
 */
public class TextStringTtlvDeserializer extends TtlvDeserializer<String> {
  private final EncodingType type = EncodingType.TEXT_STRING;

  @Override
  public String deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return new String(ttlvBuffer.array());
  }
}
