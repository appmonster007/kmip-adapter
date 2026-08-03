package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;

public class ValidationVersionMinorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationVersionMinor,
        ValidationVersionMinor.ValidationVersionMinorBuilder> {

  public ValidationVersionMinorJsonDeserializer() {
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