package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationType, ValidationType.ValidationTypeBuilder> {

  public ValidationTypeXmlDeserializer() {
    super(ValidationType.kmipTag, ValidationType.encodingType);
  }

  @Override
  protected ValidationType.ValidationTypeBuilder createBuilder() {
    return ValidationType.builder();
  }

  @Override
  protected void setValue(ValidationType.ValidationTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ValidationType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ValidationType build(ValidationType.ValidationTypeBuilder builder) {
    return builder.build();
  }
}