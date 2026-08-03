package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ServerUri;

public class ServerUriTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

  public ServerUriTtlvDeserializer() {
    super(ServerUri.kmipTag, ServerUri.encodingType);
  }

  @Override
  protected ServerUri.ServerUriBuilder createBuilder() {
    return ServerUri.builder();
  }

  @Override
  protected void setValue(ServerUri.ServerUriBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ServerUri build(ServerUri.ServerUriBuilder builder) {
    return builder.build();
  }
}