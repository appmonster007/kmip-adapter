package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyThresholdTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SplitKeyThreshold,
        SplitKeyThreshold.SplitKeyThresholdBuilder> {

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
