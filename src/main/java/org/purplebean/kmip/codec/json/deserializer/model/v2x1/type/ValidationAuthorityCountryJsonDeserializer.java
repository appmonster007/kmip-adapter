package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityCountry;

/**
 * JSON deserializer for {@link ValidationAuthorityCountry}.
 */
public class ValidationAuthorityCountryJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationAuthorityCountry,
        ValidationAuthorityCountry.ValidationAuthorityCountryBuilder> {

  /**
   * Constructs a new {@link ValidationAuthorityCountryJsonDeserializer}.
   */
  public ValidationAuthorityCountryJsonDeserializer() {
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