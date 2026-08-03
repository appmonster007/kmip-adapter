package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.SaltedPassword;

/**
 * TTLV deserializer for {@link SaltedPassword}.
 */
public class SaltedPasswordTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SaltedPassword, SaltedPassword.SaltedPasswordBuilder> {

  /**
   * Constructs a new {@link SaltedPasswordTtlvDeserializer}.
   */
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