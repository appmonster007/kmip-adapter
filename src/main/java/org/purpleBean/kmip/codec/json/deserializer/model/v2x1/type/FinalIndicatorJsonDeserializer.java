package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;

public class FinalIndicatorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<FinalIndicator, FinalIndicator.FinalIndicatorBuilder> {

  public FinalIndicatorJsonDeserializer() {
    super(FinalIndicator.kmipTag, FinalIndicator.encodingType);
  }

  @Override
  protected FinalIndicator.FinalIndicatorBuilder createBuilder() {
    return FinalIndicator.builder();
  }

  @Override
  protected void setValue(FinalIndicator.FinalIndicatorBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected FinalIndicator build(FinalIndicator.FinalIndicatorBuilder builder) {
    return builder.build();
  }
}