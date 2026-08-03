package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;

/**
 * TTLV deserializer for {@link UsageLimitsUnit}.
 */
public class UsageLimitsUnitTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UsageLimitsUnit, UsageLimitsUnit.UsageLimitsUnitBuilder> {

  /**
   * Constructs a new {@link UsageLimitsUnitTtlvDeserializer}.
   */
  public UsageLimitsUnitTtlvDeserializer() {
    super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType);
  }

  @Override
  protected UsageLimitsUnit.UsageLimitsUnitBuilder createBuilder() {
    return UsageLimitsUnit.builder();
  }

  @Override
  protected void setValue(UsageLimitsUnit.UsageLimitsUnitBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(UsageLimitsUnit.fromValue(value));
  }

  @Override
  protected UsageLimitsUnit build(UsageLimitsUnit.UsageLimitsUnitBuilder builder) {
    return builder.build();
  }
}
