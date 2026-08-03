package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

/**
 * TTLV deserializer for {@link SplitKeyThreshold}.
 */
public class SplitKeyThresholdTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyThreshold,
        SplitKeyThreshold.SplitKeyThresholdBuilder> {

  /**
   * Constructs a new {@link SplitKeyThresholdTtlvDeserializer}.
   */
  public SplitKeyThresholdTtlvDeserializer() {
    super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType);
  }

  @Override
  protected SplitKeyThreshold.SplitKeyThresholdBuilder createBuilder() {
    return SplitKeyThreshold.builder();
  }

  @Override
  protected void setValue(SplitKeyThreshold.SplitKeyThresholdBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected SplitKeyThreshold build(SplitKeyThreshold.SplitKeyThresholdBuilder builder) {
    return builder.build();
  }
}
