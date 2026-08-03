package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;

/**
 * XML deserializer for {@link ValidationAuthorityType}.
 */
public class ValidationAuthorityTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationAuthorityType,
        ValidationAuthorityType.ValidationAuthorityTypeBuilder> {

  /**
   * Constructs a new {@link ValidationAuthorityTypeXmlDeserializer}.
   */
  public ValidationAuthorityTypeXmlDeserializer() {
    super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType);
  }

  @Override
  protected ValidationAuthorityType.ValidationAuthorityTypeBuilder createBuilder() {
    return ValidationAuthorityType.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ValidationAuthorityType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ValidationAuthorityType build(
      ValidationAuthorityType.ValidationAuthorityTypeBuilder builder) {
    return builder.build();
  }
}