package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ServerInformation;

/**
 * TTLV deserializer for {@link ServerInformation}.
 */
public class ServerInformationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ServerInformation,
        ServerInformation.ServerInformationBuilder> {

  /**
   * Constructs a new {@link ServerInformationTtlvDeserializer}.
   */
  public ServerInformationTtlvDeserializer() {
    super(ServerInformation.kmipTag, ServerInformation.encodingType);
  }

  @Override
  protected ServerInformation.ServerInformationBuilder createBuilder() {
    return ServerInformation.builder();
  }

  @Override
  protected void setValue(ServerInformation.ServerInformationBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, KmipDataType.class));
  }

  @Override
  protected ServerInformation build(ServerInformation.ServerInformationBuilder builder) {
    return builder.build();
  }
}