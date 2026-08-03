package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;

public class KeyMaterialByteStringTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyMaterialByteString,
        KeyMaterialByteString.KeyMaterialByteStringBuilder> {

  public KeyMaterialByteStringTtlvDeserializer() {
    super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType);
  }

  @Override
  protected KeyMaterialByteString.KeyMaterialByteStringBuilder createBuilder() {
    return KeyMaterialByteString.builder();
  }

  @Override
  protected void setValue(KeyMaterialByteString.KeyMaterialByteStringBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected KeyMaterialByteString build(
      KeyMaterialByteString.KeyMaterialByteStringBuilder builder) {
    return builder.build();
  }
}
