package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;

public class KeyMaterialByteStringXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<KeyMaterialByteString,
        KeyMaterialByteString.KeyMaterialByteStringBuilder> {

  public KeyMaterialByteStringXmlDeserializer() {
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