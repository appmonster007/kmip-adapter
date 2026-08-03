package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;

public class PredictionResistanceJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PredictionResistance,
        PredictionResistance.PredictionResistanceBuilder> {

  public PredictionResistanceJsonDeserializer() {
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