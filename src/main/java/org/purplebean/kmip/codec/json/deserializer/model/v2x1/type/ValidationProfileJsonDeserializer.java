package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;

/**
 * JSON deserializer for {@link ValidationProfile}.
 */
public class ValidationProfileJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationProfile,
        ValidationProfile.ValidationProfileBuilder> {

  /**
   * Constructs a new {@link ValidationProfileJsonDeserializer}.
   */
  public ValidationProfileJsonDeserializer() {
    super(ValidationProfile.kmipTag, ValidationProfile.encodingType);
  }

  @Override
  protected ValidationProfile.ValidationProfileBuilder createBuilder() {
    return ValidationProfile.builder();
  }

  @Override
  protected void setValue(ValidationProfile.ValidationProfileBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationProfile build(ValidationProfile.ValidationProfileBuilder builder) {
    return builder.build();
  }
}