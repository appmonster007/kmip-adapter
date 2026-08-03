package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyFormatType, KeyFormatType.KeyFormatTypeBuilder> {

  public KeyFormatTypeTtlvDeserializer() {
    super(KeyFormatType.kmipTag, KeyFormatType.encodingType);
  }

  @Override
  protected KeyFormatType.KeyFormatTypeBuilder createBuilder() {
    return KeyFormatType.builder();
  }

  @Override
  protected void setValue(KeyFormatType.KeyFormatTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(KeyFormatType.fromValue(value));
  }

  @Override
  protected KeyFormatType build(KeyFormatType.KeyFormatTypeBuilder builder) {
    return builder.build();
  }
}
