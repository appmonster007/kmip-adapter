package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;

public class ServerCorrelationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ServerCorrelationValue,
        ServerCorrelationValue.ServerCorrelationValueBuilder> {

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