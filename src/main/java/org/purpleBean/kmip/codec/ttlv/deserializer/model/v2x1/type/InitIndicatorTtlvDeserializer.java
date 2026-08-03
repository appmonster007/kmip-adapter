package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.InitIndicator;

public class InitIndicatorTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InitIndicator, InitIndicator.InitIndicatorBuilder> {

  public InitIndicatorTtlvDeserializer() {
    super(InitIndicator.kmipTag, InitIndicator.encodingType);
  }

  @Override
  protected InitIndicator.InitIndicatorBuilder createBuilder() {
    return InitIndicator.builder();
  }

  @Override
  protected void setValue(InitIndicator.InitIndicatorBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected InitIndicator build(InitIndicator.InitIndicatorBuilder builder) {
    return builder.build();
  }
}