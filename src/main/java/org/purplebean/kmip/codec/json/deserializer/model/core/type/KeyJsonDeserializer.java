package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.Key;

public class KeyJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Key, Key.KeyBuilder> {

  public KeyJsonDeserializer() {
    super(Key.kmipTag, Key.encodingType);
  }

  @Override
  protected Key.KeyBuilder createBuilder() {
    return Key.builder();
  }

  @Override
  protected void setValue(Key.KeyBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Key build(Key.KeyBuilder builder) {
    return builder.build();
  }
}
