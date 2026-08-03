package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ValidationCertificateUri;

public class ValidationCertificateUriJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationCertificateUri,
        ValidationCertificateUri.ValidationCertificateUriBuilder> {

  public ValidationCertificateUriJsonDeserializer() {
    super(ValidationCertificateUri.kmipTag, ValidationCertificateUri.encodingType);
  }

  @Override
  protected ValidationCertificateUri.ValidationCertificateUriBuilder createBuilder() {
    return ValidationCertificateUri.builder();
  }

  @Override
  protected void setValue(ValidationCertificateUri.ValidationCertificateUriBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationCertificateUri build(
      ValidationCertificateUri.ValidationCertificateUriBuilder builder) {
    return builder.build();
  }
}