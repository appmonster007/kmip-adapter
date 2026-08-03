package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;

public class ValidationCertificateIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationCertificateIdentifier,
        ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder> {

  public ValidationCertificateIdentifierJsonDeserializer() {
    super(ValidationCertificateIdentifier.kmipTag, ValidationCertificateIdentifier.encodingType);
  }

  @Override
  protected ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder createBuilder() {
    return ValidationCertificateIdentifier.builder();
  }

  @Override
  protected void setValue(
      ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationCertificateIdentifier build(
      ValidationCertificateIdentifier.ValidationCertificateIdentifierBuilder builder) {
    return builder.build();
  }
}