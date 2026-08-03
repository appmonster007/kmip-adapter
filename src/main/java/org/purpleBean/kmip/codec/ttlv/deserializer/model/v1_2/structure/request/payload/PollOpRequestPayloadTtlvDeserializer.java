package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;

public class PollOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PollOpRequestPayload,
        PollOpRequestPayload.PollOpRequestPayloadBuilder> {

  public PollOpRequestPayloadTtlvDeserializer() {
    super(PollOpRequestPayload.kmipTag, PollOpRequestPayload.encodingType);
  }

  @Override
  protected PollOpRequestPayload.PollOpRequestPayloadBuilder createBuilder() {
    return PollOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PollOpRequestPayload.PollOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
      builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PollOpRequestPayload build(PollOpRequestPayload.PollOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
