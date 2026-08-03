package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;

/**
 * JSON deserializer for {@link KeyWrapType}.
 */
public class KeyWrapTypeJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<KeyWrapType, KeyWrapType.KeyWrapTypeBuilder> {

  /**
   * Constructs a new {@link KeyWrapTypeJsonDeserializer}.
   */
  public KeyWrapTypeJsonDeserializer() {
    super(KeyWrapType.kmipTag, KeyWrapType.encodingType);
  }

  @Override
  protected KeyWrapType.KeyWrapTypeBuilder createBuilder() {
    return KeyWrapType.builder();
  }

  @Override
  protected void setValue(KeyWrapType.KeyWrapTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(KeyWrapType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected KeyWrapType build(KeyWrapType.KeyWrapTypeBuilder builder) {
    return builder.build();
  }
}
