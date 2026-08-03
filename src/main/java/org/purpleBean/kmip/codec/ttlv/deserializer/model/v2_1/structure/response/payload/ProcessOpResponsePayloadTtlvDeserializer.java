package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;

public class ProcessOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ProcessOpResponsePayload,
        ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder> {

  public ProcessOpResponsePayloadTtlvDeserializer() {
    super(ProcessOpResponsePayload.kmipTag, ProcessOpResponsePayload.encodingType);
  }

  @Override
  protected ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder createBuilder() {
    return ProcessOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder,
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
  protected ProcessOpResponsePayload build(
      ProcessOpResponsePayload.ProcessOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}