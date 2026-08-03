package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousCorrelationValues;

public class AsynchronousCorrelationValuesTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AsynchronousCorrelationValues,
        AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder> {

  public AsynchronousCorrelationValuesTtlvDeserializer() {
    super(AsynchronousCorrelationValues.kmipTag, AsynchronousCorrelationValues.encodingType);
  }

  @Override
  protected AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder createBuilder() {
    return AsynchronousCorrelationValues.builder();
  }

  @Override
  protected void setValue(
      AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AsynchronousCorrelationValues build(
      AsynchronousCorrelationValues.AsynchronousCorrelationValuesBuilder builder) {
    return builder.build();
  }
}