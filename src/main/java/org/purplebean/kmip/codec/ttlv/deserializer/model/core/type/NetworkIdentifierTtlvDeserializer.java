package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;

/**
 * TTLV deserializer for {@link NetworkIdentifier}.
 */
public class NetworkIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NetworkIdentifier,
        NetworkIdentifier.NetworkIdentifierBuilder> {

  /**
   * Constructs a new {@link NetworkIdentifierTtlvDeserializer}.
   */
  public NetworkIdentifierTtlvDeserializer() {
    super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType);
  }

  @Override
  protected NetworkIdentifier.NetworkIdentifierBuilder createBuilder() {
    return NetworkIdentifier.builder();
  }

  @Override
  protected void setValue(NetworkIdentifier.NetworkIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected NetworkIdentifier build(NetworkIdentifier.NetworkIdentifierBuilder builder) {
    return builder.build();
  }
}
