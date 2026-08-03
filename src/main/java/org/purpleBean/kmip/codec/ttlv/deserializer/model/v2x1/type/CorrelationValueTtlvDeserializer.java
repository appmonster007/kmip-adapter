package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

public class CorrelationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CorrelationValue,
        CorrelationValue.CorrelationValueBuilder> {

  public CorrelationValueTtlvDeserializer() {
    super(CorrelationValue.kmipTag, CorrelationValue.encodingType);
  }

  @Override
  protected CorrelationValue.CorrelationValueBuilder createBuilder() {
    return CorrelationValue.builder();
  }

  @Override
  protected void setValue(CorrelationValue.CorrelationValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, ByteBuffer.class));
  }

  @Override
  protected CorrelationValue build(CorrelationValue.CorrelationValueBuilder builder) {
    return builder.build();
  }
}