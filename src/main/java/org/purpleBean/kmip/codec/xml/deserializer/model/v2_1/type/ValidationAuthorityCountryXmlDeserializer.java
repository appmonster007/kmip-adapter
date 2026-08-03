package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityCountry;

public class ValidationAuthorityCountryXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationAuthorityCountry,
        ValidationAuthorityCountry.ValidationAuthorityCountryBuilder> {

  public ValidationAuthorityCountryXmlDeserializer() {
    super(ValidationAuthorityCountry.kmipTag, ValidationAuthorityCountry.encodingType);
  }

  @Override
  protected ValidationAuthorityCountry.ValidationAuthorityCountryBuilder createBuilder() {
    return ValidationAuthorityCountry.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityCountry.ValidationAuthorityCountryBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationAuthorityCountry build(
      ValidationAuthorityCountry.ValidationAuthorityCountryBuilder builder) {
    return builder.build();
  }
}