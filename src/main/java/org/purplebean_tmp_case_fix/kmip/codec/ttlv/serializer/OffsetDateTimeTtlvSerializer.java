package org.purplebean.kmip.codec.ttlv.serializer;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

public class OffsetDateTimeTtlvSerializer extends TtlvSerializer<OffsetDateTime> {
  private final EncodingType type = EncodingType.DATE_TIME;

  @Override
  public ByteBuffer serialize(OffsetDateTime value, TtlvMapper mapper) throws IOException {
    ByteBuffer bb = ByteBuffer.allocate(type.getRawByteSize());
    bb.putLong(value.toEpochSecond());
    return bb;
  }
}
