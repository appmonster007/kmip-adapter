package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityUri;

/**
 * JSON deserializer for {@link ValidationAuthorityUri}.
 */
public class ValidationAuthorityUriJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ValidationAuthorityUri,
        ValidationAuthorityUri.ValidationAuthorityUriBuilder> {

  /**
   * Constructs a new {@link ValidationAuthorityUriJsonDeserializer}.
   */
  public ValidationAuthorityUriJsonDeserializer() {
    super(ValidationAuthorityUri.kmipTag, ValidationAuthorityUri.encodingType);
  }

  @Override
  protected ValidationAuthorityUri.ValidationAuthorityUriBuilder createBuilder() {
    return ValidationAuthorityUri.builder();
  }

  @Override
  protected void setValue(ValidationAuthorityUri.ValidationAuthorityUriBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ValidationAuthorityUri build(
      ValidationAuthorityUri.ValidationAuthorityUriBuilder builder) {
    return builder.build();
  }
}