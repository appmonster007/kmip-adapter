package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;

/**
 * TTLV deserializer for {@link CancelOpResponsePayload}.
 */
public class CancelOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CancelOpResponsePayload,
        CancelOpResponsePayload.CancelOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link CancelOpResponsePayloadTtlvDeserializer}.
   */
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
