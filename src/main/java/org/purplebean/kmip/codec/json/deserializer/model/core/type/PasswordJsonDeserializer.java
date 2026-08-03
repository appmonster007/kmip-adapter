package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.Password;

/**
 * JSON deserializer for {@link Password}.
 */
public class PasswordJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Password, Password.PasswordBuilder> {

  /**
   * Constructs a new {@link PasswordJsonDeserializer}.
   */
  public PasswordJsonDeserializer() {
    super(Password.kmipTag, Password.encodingType);
  }

  @Override
  protected Password.PasswordBuilder createBuilder() {
    return Password.builder();
  }

  @Override
  protected void setValue(Password.PasswordBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Password build(Password.PasswordBuilder builder) {
    return builder.build();
  }
}
