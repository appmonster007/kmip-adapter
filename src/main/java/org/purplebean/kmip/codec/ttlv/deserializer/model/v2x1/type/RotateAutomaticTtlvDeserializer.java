package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.RotateAutomatic;

/**
 * TTLV deserializer for {@link RotateAutomatic}.
 */
public class RotateAutomaticTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateAutomatic, RotateAutomatic.RotateAutomaticBuilder> {

  /**
   * Constructs a new {@link RotateAutomaticTtlvDeserializer}.
   */
  public RotateAutomaticTtlvDeserializer() {
    super(RotateAutomatic.kmipTag, RotateAutomatic.encodingType);
  }

  @Override
  protected RotateAutomatic.RotateAutomaticBuilder createBuilder() {
    return RotateAutomatic.builder();
  }

  @Override
  protected void setValue(RotateAutomatic.RotateAutomaticBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected RotateAutomatic build(RotateAutomatic.RotateAutomaticBuilder builder) {
    return builder.build();
  }
}