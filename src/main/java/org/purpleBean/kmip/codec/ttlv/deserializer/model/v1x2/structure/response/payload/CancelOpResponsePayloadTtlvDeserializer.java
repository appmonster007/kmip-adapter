package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;

public class CancelOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CancelOpResponsePayload,
        CancelOpResponsePayload.CancelOpResponsePayloadBuilder> {

  public CancelOpResponsePayloadTtlvDeserializer() {
    super(CancelOpResponsePayload.kmipTag, CancelOpResponsePayload.encodingType);
  }

  @Override
  protected CancelOpResponsePayload.CancelOpResponsePayloadBuilder createBuilder() {
    return CancelOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      case KmipTag.Standard.CANCELLATION_RESULT ->
          builder.cancellationResult(mapper.readValue(p, CancellationResult.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CancelOpResponsePayload build(
      CancelOpResponsePayload.CancelOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
