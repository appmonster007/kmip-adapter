package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.ServerHashedPassword;

/**
 * TTLV deserializer for {@link ServerHashedPassword}.
 */
public class ServerHashedPasswordTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ServerHashedPassword,
        ServerHashedPassword.ServerHashedPasswordBuilder> {

  /**
   * Constructs a new {@link ServerHashedPasswordTtlvDeserializer}.
   */
  public ServerHashedPasswordTtlvDeserializer() {
    super(ServerHashedPassword.kmipTag, ServerHashedPassword.encodingType);
  }

  @Override
  protected ServerHashedPassword.ServerHashedPasswordBuilder createBuilder() {
    return ServerHashedPassword.builder();
  }

  @Override
  protected void setValue(ServerHashedPassword.ServerHashedPasswordBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected ServerHashedPassword build(ServerHashedPassword.ServerHashedPasswordBuilder builder) {
    return builder.build();
  }
}