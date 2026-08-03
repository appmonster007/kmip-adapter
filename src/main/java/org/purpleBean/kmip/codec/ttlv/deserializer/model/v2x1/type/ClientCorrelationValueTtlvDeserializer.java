package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.ClientCorrelationValue;

public class ClientCorrelationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ClientCorrelationValue,
        ClientCorrelationValue.ClientCorrelationValueBuilder> {

  public ClientCorrelationValueTtlvDeserializer() {
    super(ClientCorrelationValue.kmipTag, ClientCorrelationValue.encodingType);
  }

  @Override
  protected ClientCorrelationValue.ClientCorrelationValueBuilder createBuilder() {
    return ClientCorrelationValue.builder();
  }

  @Override
  protected void setValue(ClientCorrelationValue.ClientCorrelationValueBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected ClientCorrelationValue build(
      ClientCorrelationValue.ClientCorrelationValueBuilder builder) {
    return builder.build();
  }
}