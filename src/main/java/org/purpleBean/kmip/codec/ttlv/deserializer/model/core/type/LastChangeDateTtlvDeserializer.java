package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

public class LastChangeDateTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LastChangeDate, LastChangeDate.LastChangeDateBuilder> {

  public LastChangeDateTtlvDeserializer() {
    super(LastChangeDate.kmipTag, LastChangeDate.encodingType);
  }

  @Override
  protected LastChangeDate.LastChangeDateBuilder createBuilder() {
    return LastChangeDate.builder();
  }

  @Override
  protected void setValue(LastChangeDate.LastChangeDateBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
  }

  @Override
  protected LastChangeDate build(LastChangeDate.LastChangeDateBuilder builder) {
    return builder.build();
  }
}
