package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;

/**
 * TTLV deserializer for {@link PollOpResponsePayload}.
 */
public class PollOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PollOpResponsePayload,
        PollOpResponsePayload.PollOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link PollOpResponsePayloadTtlvDeserializer}.
   */
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