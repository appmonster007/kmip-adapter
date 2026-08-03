package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.LeaseTime;

/**
 * TTLV deserializer for {@link LeaseTime}.
 */
public class LeaseTimeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<LeaseTime, LeaseTime.LeaseTimeBuilder> {

  /**
   * Constructs a new {@link LeaseTimeTtlvDeserializer}.
   */
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
