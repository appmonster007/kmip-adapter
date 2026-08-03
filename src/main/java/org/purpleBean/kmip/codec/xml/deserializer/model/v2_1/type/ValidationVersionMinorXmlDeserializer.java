package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;

public class ValidationVersionMinorXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidationVersionMinor,
        ValidationVersionMinor.ValidationVersionMinorBuilder> {

  public ValidationVersionMinorXmlDeserializer() {
    super(ValidationVersionMinor.kmipTag, ValidationVersionMinor.encodingType);
  }

  @Override
  protected ValidationVersionMinor.ValidationVersionMinorBuilder createBuilder() {
    return ValidationVersionMinor.builder();
  }

  @Override
  protected void setValue(ValidationVersionMinor.ValidationVersionMinorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ValidationVersionMinor build(
      ValidationVersionMinor.ValidationVersionMinorBuilder builder) {
    return builder.build();
  }
}