package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ServerUri;

/**
 * TTLV deserializer for {@link ServerUri}.
 */
public class ServerUriTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

  /**
   * Constructs a new {@link ServerUriTtlvDeserializer}.
   */
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