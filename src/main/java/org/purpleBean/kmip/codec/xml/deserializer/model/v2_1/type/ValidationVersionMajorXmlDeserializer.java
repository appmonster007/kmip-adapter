package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;

public class ValidationVersionMajorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationVersionMajor,
        ValidationVersionMajor.ValidationVersionMajorBuilder> {

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