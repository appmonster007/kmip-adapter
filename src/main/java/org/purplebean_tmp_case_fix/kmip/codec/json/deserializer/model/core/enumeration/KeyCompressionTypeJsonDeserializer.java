package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyCompressionType,
        KeyCompressionType.KeyCompressionTypeBuilder> {

  public KeyCompressionTypeJsonDeserializer() {
    super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType);
  }

  @Override
  protected KeyCompressionType.KeyCompressionTypeBuilder createBuilder() {
    return KeyCompressionType.builder();
  }

  @Override
  protected void setValue(KeyCompressionType.KeyCompressionTypeBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(KeyCompressionType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected KeyCompressionType build(KeyCompressionType.KeyCompressionTypeBuilder builder) {
    return builder.build();
  }
}
