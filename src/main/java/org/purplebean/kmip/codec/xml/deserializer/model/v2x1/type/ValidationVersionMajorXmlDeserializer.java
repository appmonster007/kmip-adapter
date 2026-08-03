package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;

/**
 * XML deserializer for {@link ValidationVersionMajor}.
 */
public class ValidationVersionMajorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationVersionMajor,
        ValidationVersionMajor.ValidationVersionMajorBuilder> {

  /**
   * Constructs a new {@link ValidationVersionMajorXmlDeserializer}.
   */
  public ValidationVersionMajorXmlDeserializer() {
    super(ValidationVersionMajor.kmipTag, ValidationVersionMajor.encodingType);
  }

  @Override
  protected ValidationVersionMajor.ValidationVersionMajorBuilder createBuilder() {
    return ValidationVersionMajor.builder();
  }

  @Override
  protected void setValue(ValidationVersionMajor.ValidationVersionMajorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ValidationVersionMajor build(
      ValidationVersionMajor.ValidationVersionMajorBuilder builder) {
    return builder.build();
  }
}