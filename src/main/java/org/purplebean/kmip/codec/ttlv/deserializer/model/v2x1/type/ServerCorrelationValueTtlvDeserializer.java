package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ServerCorrelationValue;

/**
 * TTLV deserializer for {@link ServerCorrelationValue}.
 */
public class ServerCorrelationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ServerCorrelationValue,
        ServerCorrelationValue.ServerCorrelationValueBuilder> {

  /**
   * Constructs a new {@link ServerCorrelationValueTtlvDeserializer}.
   */
  public ServerCorrelationValueTtlvDeserializer() {
    super(ServerCorrelationValue.kmipTag, ServerCorrelationValue.encodingType);
  }

  @Override
  protected ServerCorrelationValue.ServerCorrelationValueBuilder createBuilder() {
    return ServerCorrelationValue.builder();
  }

  @Override
  protected void setValue(ServerCorrelationValue.ServerCorrelationValueBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ServerCorrelationValue build(
      ServerCorrelationValue.ServerCorrelationValueBuilder builder) {
    return builder.build();
  }
}