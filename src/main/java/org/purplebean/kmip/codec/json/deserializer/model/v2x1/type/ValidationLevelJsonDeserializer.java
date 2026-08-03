package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;

/**
 * JSON deserializer for {@link ValidationLevel}.
 */
public class ValidationLevelJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationLevel, ValidationLevel.ValidationLevelBuilder> {

  /**
   * Constructs a new {@link ValidationLevelJsonDeserializer}.
   */
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