package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

public class KeyValueByteStringJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyValueByteString,
        KeyValueByteString.KeyValueByteStringBuilder> {

  public KeyValueByteStringJsonDeserializer() {
    super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType);
  }

  @Override
  protected KeyValueByteString.KeyValueByteStringBuilder createBuilder() {
    return KeyValueByteString.builder();
  }

  @Override
  protected void setValue(KeyValueByteString.KeyValueByteStringBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected KeyValueByteString build(KeyValueByteString.KeyValueByteStringBuilder builder) {
    return builder.build();
  }
}
