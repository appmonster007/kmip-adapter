package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Key;

public class KeyXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Key, Key.KeyBuilder> {

  public KeyXmlDeserializer() {
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