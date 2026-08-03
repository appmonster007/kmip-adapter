package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;

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