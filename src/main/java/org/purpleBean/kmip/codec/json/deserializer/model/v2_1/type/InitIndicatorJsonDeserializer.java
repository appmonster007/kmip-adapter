package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

public class InitIndicatorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InitIndicator, InitIndicator.InitIndicatorBuilder> {

  public InitIndicatorJsonDeserializer() {
    super(InitIndicator.kmipTag, InitIndicator.encodingType);
  }

  @Override
  protected InitIndicator.InitIndicatorBuilder createBuilder() {
    return InitIndicator.builder();
  }

  @Override
  protected void setValue(InitIndicator.InitIndicatorBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected InitIndicator build(InitIndicator.InitIndicatorBuilder builder) {
    return builder.build();
  }
}