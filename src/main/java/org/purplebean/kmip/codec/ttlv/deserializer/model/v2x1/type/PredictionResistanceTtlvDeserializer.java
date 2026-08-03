package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;

/**
 * TTLV deserializer for {@link PredictionResistance}.
 */
public class PredictionResistanceTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PredictionResistance,
        PredictionResistance.PredictionResistanceBuilder> {

  /**
   * Constructs a new {@link PredictionResistanceTtlvDeserializer}.
   */
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