package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.RotateInterval;

/**
 * TTLV deserializer for {@link RotateInterval}.
 */
public class RotateIntervalTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateInterval, RotateInterval.RotateIntervalBuilder> {

  /**
   * Constructs a new {@link RotateIntervalTtlvDeserializer}.
   */
  public RotateIntervalTtlvDeserializer() {
    super(RotateInterval.kmipTag, RotateInterval.encodingType);
  }

  @Override
  protected RotateInterval.RotateIntervalBuilder createBuilder() {
    return RotateInterval.builder();
  }

  @Override
  protected void setValue(RotateInterval.RotateIntervalBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Long.class));
  }

  @Override
  protected RotateInterval build(RotateInterval.RotateIntervalBuilder builder) {
    return builder.build();
  }
}