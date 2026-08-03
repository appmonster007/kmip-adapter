package org.purpleBean.kmip.codec.ttlv.serializer;


import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;

public class ByteStringTtlvSerializer extends TtlvSerializer<ByteBuffer> {
  private final EncodingType type = EncodingType.BYTE_STRING;

  @Override
  public ByteBuffer serialize(ByteBuffer value, TtlvMapper mapper) throws IOException {
    return value;
  }
}
