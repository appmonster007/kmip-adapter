package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RecommendedCurve,
        RecommendedCurve.RecommendedCurveBuilder> {

  public RecommendedCurveTtlvDeserializer() {
    super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType);
  }

  @Override
  protected RecommendedCurve.RecommendedCurveBuilder createBuilder() {
    return RecommendedCurve.builder();
  }

  @Override
  protected void setValue(RecommendedCurve.RecommendedCurveBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(RecommendedCurve.fromValue(value));
  }

  @Override
  protected RecommendedCurve build(RecommendedCurve.RecommendedCurveBuilder builder) {
    return builder.build();
  }
}
