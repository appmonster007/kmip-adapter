package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationType,
        KeyValueLocationType.KeyValueLocationTypeBuilder> {

  public KeyValueLocationTypeTtlvDeserializer() {
    super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType);
  }

  @Override
  protected KeyValueLocationType.KeyValueLocationTypeBuilder createBuilder() {
    return KeyValueLocationType.builder();
  }

  @Override
  protected void setValue(KeyValueLocationType.KeyValueLocationTypeBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(KeyValueLocationType.fromValue(value));
  }

  @Override
  protected KeyValueLocationType build(KeyValueLocationType.KeyValueLocationTypeBuilder builder) {
    return builder.build();
  }
}
