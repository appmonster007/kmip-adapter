package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

public class KeyMaterialByteStringJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyMaterialByteString,
        KeyMaterialByteString.KeyMaterialByteStringBuilder> {

  public KeyMaterialByteStringJsonDeserializer() {
    super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType);
  }

  @Override
  protected KeyMaterialByteString.KeyMaterialByteStringBuilder createBuilder() {
    return KeyMaterialByteString.builder();
  }

  @Override
  protected void setValue(KeyMaterialByteString.KeyMaterialByteStringBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected KeyMaterialByteString build(
      KeyMaterialByteString.KeyMaterialByteStringBuilder builder) {
    return builder.build();
  }
}
