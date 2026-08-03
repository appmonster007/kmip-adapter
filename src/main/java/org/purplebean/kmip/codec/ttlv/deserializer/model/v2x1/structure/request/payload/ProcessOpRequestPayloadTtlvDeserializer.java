package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ProcessOpRequestPayload;

/**
 * TTLV deserializer for {@link ProcessOpRequestPayload}.
 */
public class ProcessOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProcessOpRequestPayload,
        ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ProcessOpRequestPayloadTtlvDeserializer}.
   */
  public ProcessOpRequestPayloadTtlvDeserializer() {
    super(ProcessOpRequestPayload.kmipTag, ProcessOpRequestPayload.encodingType);
  }

  @Override
  protected ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder createBuilder() {
    return ProcessOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE -> builder.asynchronousCorrelationValue(
          mapper.readValue(p, AsynchronousCorrelationValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ProcessOpRequestPayload build(
      ProcessOpRequestPayload.ProcessOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}