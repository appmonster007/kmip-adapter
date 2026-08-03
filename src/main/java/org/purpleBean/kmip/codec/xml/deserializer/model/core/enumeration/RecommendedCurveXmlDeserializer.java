package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RecommendedCurve,
        RecommendedCurve.RecommendedCurveBuilder> {

  public RecommendedCurveXmlDeserializer() {
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