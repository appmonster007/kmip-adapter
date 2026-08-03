package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityUri;

public class ValidationAuthorityUriXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationAuthorityUri,
        ValidationAuthorityUri.ValidationAuthorityUriBuilder> {

  public ValidationAuthorityUriXmlDeserializer() {
    super(ValidationAuthorityUri.kmipTag, ValidationAuthorityUri.encodingType);
  }

  @Override
  protected ValidationAuthorityUri.ValidationAuthorityUriBuilder createBuilder() {
    return ValidationAuthorityUri.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityUri.ValidationAuthorityUriBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationAuthorityUri build(
      ValidationAuthorityUri.ValidationAuthorityUriBuilder builder) {
    return builder.build();
  }
}