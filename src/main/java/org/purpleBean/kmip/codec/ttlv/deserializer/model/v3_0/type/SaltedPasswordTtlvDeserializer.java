package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;

public class SaltedPasswordTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SaltedPassword, SaltedPassword.SaltedPasswordBuilder> {

  public SaltedPasswordTtlvDeserializer() {
    super(SaltedPassword.kmipTag, SaltedPassword.encodingType);
  }

  @Override
  protected SaltedPassword.SaltedPasswordBuilder createBuilder() {
    return SaltedPassword.builder();
  }

  @Override
  protected void setValue(SaltedPassword.SaltedPasswordBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SaltedPassword build(SaltedPassword.SaltedPasswordBuilder builder) {
    return builder.build();
  }
}