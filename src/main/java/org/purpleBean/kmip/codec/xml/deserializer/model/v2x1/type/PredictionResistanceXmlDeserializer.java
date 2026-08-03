package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.PredictionResistance;

public class PredictionResistanceXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PredictionResistance,
        PredictionResistance.PredictionResistanceBuilder> {

  public PredictionResistanceXmlDeserializer() {
    super(PredictionResistance.kmipTag, PredictionResistance.encodingType);
  }

  @Override
  protected PredictionResistance.PredictionResistanceBuilder createBuilder() {
    return PredictionResistance.builder();
  }

  @Override
  protected void setValue(PredictionResistance.PredictionResistanceBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected PredictionResistance build(PredictionResistance.PredictionResistanceBuilder builder) {
    return builder.build();
  }
}