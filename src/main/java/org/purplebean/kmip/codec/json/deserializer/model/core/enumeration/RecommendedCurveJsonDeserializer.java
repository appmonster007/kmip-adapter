package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;

/**
 * JSON deserializer for {@link RecommendedCurve}.
 */
public class RecommendedCurveJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<RecommendedCurve,
        RecommendedCurve.RecommendedCurveBuilder> {

  /**
   * Constructs a new {@link RecommendedCurveJsonDeserializer}.
   */
  public RecommendedCurveJsonDeserializer() {
    super(RecommendedCurve.kmipTag, RecommendedCurve.encodingType);
  }

  @Override
  protected RecommendedCurve.RecommendedCurveBuilder createBuilder() {
    return RecommendedCurve.builder();
  }

  @Override
  protected void setValue(RecommendedCurve.RecommendedCurveBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(RecommendedCurve.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RecommendedCurve build(RecommendedCurve.RecommendedCurveBuilder builder) {
    return builder.build();
  }
}
