package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Key;

public class KeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Key, Key.KeyBuilder> {

  public KeyTtlvDeserializer() {
    super(Key.kmipTag, Key.encodingType);
  }

  @Override
  protected Key.KeyBuilder createBuilder() {
    return Key.builder();
  }

  @Override
  protected void setValue(Key.KeyBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected Key build(Key.KeyBuilder builder) {
    return builder.build();
  }
}
