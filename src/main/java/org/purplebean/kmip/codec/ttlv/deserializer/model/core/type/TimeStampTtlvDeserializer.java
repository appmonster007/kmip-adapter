package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.TimeStamp;

public class TimeStampTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<TimeStamp, TimeStamp.TimeStampBuilder> {

  public TimeStampTtlvDeserializer() {
    super(TimeStamp.kmipTag, TimeStamp.encodingType);
  }

  @Override
  protected TimeStamp.TimeStampBuilder createBuilder() {
    return TimeStamp.builder();
  }

  @Override
  protected void setValue(TimeStamp.TimeStampBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected TimeStamp build(TimeStamp.TimeStampBuilder builder) {
    return builder.build();
  }
}
