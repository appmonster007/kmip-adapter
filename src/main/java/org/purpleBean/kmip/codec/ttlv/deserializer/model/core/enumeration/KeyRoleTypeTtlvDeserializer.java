package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<KeyRoleType, KeyRoleType.KeyRoleTypeBuilder> {

  public KeyRoleTypeTtlvDeserializer() {
    super(KeyRoleType.kmipTag, KeyRoleType.encodingType);
  }

  @Override
  protected KeyRoleType.KeyRoleTypeBuilder createBuilder() {
    return KeyRoleType.builder();
  }

  @Override
  protected void setValue(KeyRoleType.KeyRoleTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(KeyRoleType.fromValue(value));
  }

  @Override
  protected KeyRoleType build(KeyRoleType.KeyRoleTypeBuilder builder) {
    return builder.build();
  }
}
