package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;

/**
 * JSON deserializer for {@link KeyValueLocationType}.
 */
public class KeyValueLocationTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyValueLocationType,
        KeyValueLocationType.KeyValueLocationTypeBuilder> {

  /**
   * Constructs a new {@link KeyValueLocationTypeJsonDeserializer}.
   */
  public KeyValueLocationTypeJsonDeserializer() {
    super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType);
  }

  @Override
  protected KeyValueLocationType.KeyValueLocationTypeBuilder createBuilder() {
    return KeyValueLocationType.builder();
  }

  @Override
  protected void setValue(KeyValueLocationType.KeyValueLocationTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(KeyValueLocationType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected KeyValueLocationType build(KeyValueLocationType.KeyValueLocationTypeBuilder builder) {
    return builder.build();
  }
}
