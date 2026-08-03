package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

public class HashedUsernamePasswordTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<HashedUsernamePassword,
        HashedUsernamePassword.HashedUsernamePasswordBuilder> {

  public HashedUsernamePasswordTtlvDeserializer() {
    super(HashedUsernamePassword.kmipTag, HashedUsernamePassword.encodingType);
  }

  @Override
  protected HashedUsernamePassword.HashedUsernamePasswordBuilder createBuilder() {
    return HashedUsernamePassword.builder();
  }

  @Override
  protected void setValue(HashedUsernamePassword.HashedUsernamePasswordBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedUsernamePassword build(
      HashedUsernamePassword.HashedUsernamePasswordBuilder builder) {
    return builder.build();
  }
}
