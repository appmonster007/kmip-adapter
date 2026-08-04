package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ProtectionPeriod;

/**
 * TTLV deserializer for {@link ProtectionPeriod}.
 */
public class ProtectionPeriodTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProtectionPeriod,
        ProtectionPeriod.ProtectionPeriodBuilder> {

  /**
   * Constructs a new {@link ProtectionPeriodTtlvDeserializer}.
   */
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
    builder.value(mapper.readValue(p, Integer.class));
  }

  @Override
  protected ProtectionPeriod build(ProtectionPeriod.ProtectionPeriodBuilder builder) {
    return builder.build();
  }
}