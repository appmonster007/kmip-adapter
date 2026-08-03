package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;

/**
 * TTLV deserializer for {@link KeyWrapType}.
 */
public class KeyWrapTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<KeyWrapType, KeyWrapType.KeyWrapTypeBuilder> {

  /**
   * Constructs a new {@link KeyWrapTypeTtlvDeserializer}.
   */
  public KeyWrapTypeTtlvDeserializer() {
    super(KeyWrapType.kmipTag, KeyWrapType.encodingType);
  }

  @Override
  protected KeyWrapType.KeyWrapTypeBuilder createBuilder() {
    return KeyWrapType.builder();
  }

  @Override
  protected void setValue(KeyWrapType.KeyWrapTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(KeyWrapType.fromValue(value));
  }

  @Override
  protected KeyWrapType build(KeyWrapType.KeyWrapTypeBuilder builder) {
    return builder.build();
  }
}
