package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

public class InitIndicatorXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<InitIndicator, InitIndicator.InitIndicatorBuilder> {

  public InitIndicatorXmlDeserializer() {
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