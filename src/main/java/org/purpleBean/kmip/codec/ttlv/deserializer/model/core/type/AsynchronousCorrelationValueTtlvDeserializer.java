package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;

public class AsynchronousCorrelationValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AsynchronousCorrelationValue,
        AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder> {

  public AsynchronousCorrelationValueTtlvDeserializer() {
    super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType);
  }

  @Override
  protected AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder createBuilder() {
    return AsynchronousCorrelationValue.builder();
  }

  @Override
  protected void setValue(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder,
                          byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper)
      throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected AsynchronousCorrelationValue build(
      AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder) {
    return builder.build();
  }
}