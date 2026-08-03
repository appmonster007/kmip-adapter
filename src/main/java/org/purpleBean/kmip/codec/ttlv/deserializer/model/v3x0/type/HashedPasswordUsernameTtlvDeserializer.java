package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3x0.type.HashedPasswordUsername;

public class HashedPasswordUsernameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<HashedPasswordUsername,
        HashedPasswordUsername.HashedPasswordUsernameBuilder> {

  public HashedPasswordUsernameTtlvDeserializer() {
    super(HashedPasswordUsername.kmipTag, HashedPasswordUsername.encodingType);
  }

  @Override
  protected HashedPasswordUsername.HashedPasswordUsernameBuilder createBuilder() {
    return HashedPasswordUsername.builder();
  }

  @Override
  protected void setValue(HashedPasswordUsername.HashedPasswordUsernameBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected HashedPasswordUsername build(
      HashedPasswordUsername.HashedPasswordUsernameBuilder builder) {
    return builder.build();
  }
}
