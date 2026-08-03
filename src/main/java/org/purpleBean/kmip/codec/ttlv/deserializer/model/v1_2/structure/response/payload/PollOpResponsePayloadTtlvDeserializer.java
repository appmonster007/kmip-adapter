package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PollOpResponsePayload;

public class PollOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PollOpResponsePayload,
        PollOpResponsePayload.PollOpResponsePayloadBuilder> {

  public PollOpResponsePayloadTtlvDeserializer() {
    super(PollOpResponsePayload.kmipTag, PollOpResponsePayload.encodingType);
  }

  @Override
  protected PollOpResponsePayload.PollOpResponsePayloadBuilder createBuilder() {
    return PollOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PollOpResponsePayload.PollOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PollOpResponsePayload build(
      PollOpResponsePayload.PollOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}