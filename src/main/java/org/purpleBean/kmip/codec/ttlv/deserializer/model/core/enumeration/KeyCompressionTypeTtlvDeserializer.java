package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyCompressionType,
        KeyCompressionType.KeyCompressionTypeBuilder> {

  public KeyCompressionTypeTtlvDeserializer() {
    super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType);
  }

  @Override
  protected KeyCompressionType.KeyCompressionTypeBuilder createBuilder() {
    return KeyCompressionType.builder();
  }

  @Override
  protected void setValue(KeyCompressionType.KeyCompressionTypeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(KeyCompressionType.fromValue(value));
  }

  @Override
  protected KeyCompressionType build(KeyCompressionType.KeyCompressionTypeBuilder builder) {
    return builder.build();
  }
}
