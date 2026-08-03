package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LeaseTime, LeaseTime.LeaseTimeBuilder> {

  public LeaseTimeTtlvDeserializer() {
    super(LeaseTime.kmipTag, LeaseTime.encodingType);
  }

  @Override
  protected LeaseTime.LeaseTimeBuilder createBuilder() {
    return LeaseTime.builder();
  }

  @Override
  protected void setValue(LeaseTime.LeaseTimeBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected LeaseTime build(LeaseTime.LeaseTimeBuilder builder) {
    return builder.build();
  }
}
