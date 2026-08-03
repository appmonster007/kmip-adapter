package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;

public class FinalIndicatorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<FinalIndicator, FinalIndicator.FinalIndicatorBuilder> {

  public FinalIndicatorXmlDeserializer() {
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