package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidityIndicator,
        ValidityIndicator.ValidityIndicatorBuilder> {

  public ValidityIndicatorXmlDeserializer() {
    super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType);
  }

  @Override
  protected ValidityIndicator.ValidityIndicatorBuilder createBuilder() {
    return ValidityIndicator.builder();
  }

  @Override
  protected void setValue(ValidityIndicator.ValidityIndicatorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ValidityIndicator.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ValidityIndicator build(ValidityIndicator.ValidityIndicatorBuilder builder) {
    return builder.build();
  }
}