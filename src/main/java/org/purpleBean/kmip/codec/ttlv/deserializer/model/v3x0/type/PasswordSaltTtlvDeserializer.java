package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3x0.type.PasswordSalt;

public class PasswordSaltTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<PasswordSalt, PasswordSalt.PasswordSaltBuilder> {

  public PasswordSaltTtlvDeserializer() {
    super(PasswordSalt.kmipTag, PasswordSalt.encodingType);
  }

  @Override
  protected PasswordSalt.PasswordSaltBuilder createBuilder() {
    return PasswordSalt.builder();
  }

  @Override
  protected void setValue(PasswordSalt.PasswordSaltBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected PasswordSalt build(PasswordSalt.PasswordSaltBuilder builder) {
    return builder.build();
  }
}