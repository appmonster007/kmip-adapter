package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.PredictionResistance;

public class PredictionResistanceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PredictionResistance,
        PredictionResistance.PredictionResistanceBuilder> {

  public PredictionResistanceTtlvDeserializer() {
    super(PredictionResistance.kmipTag, PredictionResistance.encodingType);
  }

  @Override
  protected PredictionResistance.PredictionResistanceBuilder createBuilder() {
    return PredictionResistance.builder();
  }

  @Override
  protected void setValue(PredictionResistance.PredictionResistanceBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected PredictionResistance build(PredictionResistance.PredictionResistanceBuilder builder) {
    return builder.build();
  }
}