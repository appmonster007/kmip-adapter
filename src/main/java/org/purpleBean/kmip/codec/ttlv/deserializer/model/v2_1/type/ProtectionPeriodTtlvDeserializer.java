package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ProtectionPeriod;

public class ProtectionPeriodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionPeriod,
        ProtectionPeriod.ProtectionPeriodBuilder> {

  public ProtectionPeriodTtlvDeserializer() {
    super(ProtectionPeriod.kmipTag, ProtectionPeriod.encodingType);
  }

  @Override
  protected ProtectionPeriod.ProtectionPeriodBuilder createBuilder() {
    return ProtectionPeriod.builder();
  }

  @Override
  protected void setValue(ProtectionPeriod.ProtectionPeriodBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Long.class));
  }

  @Override
  protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
    return builder.build();
  }
}