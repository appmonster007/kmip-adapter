package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;

/**
 * XML deserializer for {@link ValidationCertificateUri}.
 */
public class ValidationCertificateUriXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationCertificateUri,
        ValidationCertificateUri.ValidationCertificateUriBuilder> {

  /**
   * Constructs a new {@link ValidationCertificateUriXmlDeserializer}.
   */
  public ValidationCertificateUriXmlDeserializer() {
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