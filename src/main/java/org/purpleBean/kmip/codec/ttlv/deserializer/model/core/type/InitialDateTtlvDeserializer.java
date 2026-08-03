package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.InitialDate;

public class InitialDateTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<InitialDate, InitialDate.InitialDateBuilder> {

  public InitialDateTtlvDeserializer() {
    super(InitialDate.kmipTag, InitialDate.encodingType);
  }

  @Override
  protected InitialDate.InitialDateBuilder createBuilder() {
    return InitialDate.builder();
  }

  @Override
  protected void setValue(InitialDate.InitialDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected InitialDate build(InitialDate.InitialDateBuilder builder) {
    return builder.build();
  }
}
