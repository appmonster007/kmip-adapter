package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsTotalTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UsageLimitsTotal,
        UsageLimitsTotal.UsageLimitsTotalBuilder> {

  public UsageLimitsTotalTtlvDeserializer() {
    super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType);
  }

  @Override
  protected UsageLimitsTotal.UsageLimitsTotalBuilder createBuilder() {
    return UsageLimitsTotal.builder();
  }

  @Override
  protected void setValue(UsageLimitsTotal.UsageLimitsTotalBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Long.class));
  }

  @Override
  protected UsageLimitsTotal build(UsageLimitsTotal.UsageLimitsTotalBuilder builder) {
    return builder.build();
  }
}