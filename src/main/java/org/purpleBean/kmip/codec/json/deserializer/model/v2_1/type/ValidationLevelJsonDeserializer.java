package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;

public class ValidationLevelJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationLevel, ValidationLevel.ValidationLevelBuilder> {

  public ValidationLevelJsonDeserializer() {
    super(ValidationLevel.kmipTag, ValidationLevel.encodingType);
  }

  @Override
  protected ValidationLevel.ValidationLevelBuilder createBuilder() {
    return ValidationLevel.builder();
  }

  @Override
  protected void setValue(ValidationLevel.ValidationLevelBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected ValidationLevel build(ValidationLevel.ValidationLevelBuilder builder) {
    return builder.build();
  }
}