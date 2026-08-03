package org.purpleBean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

public class ByteStringTtlvDeserializer extends TtlvDeserializer<ByteBuffer> {
  private final EncodingType type = EncodingType.BYTE_STRING;

  @Override
  public ByteBuffer deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return ttlvBuffer;
  }
}
