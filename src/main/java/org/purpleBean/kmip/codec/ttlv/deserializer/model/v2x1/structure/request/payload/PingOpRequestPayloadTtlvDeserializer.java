package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;

public class PingOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PingOpRequestPayload,
        PingOpRequestPayload.PingOpRequestPayloadBuilder> {

  public PingOpRequestPayloadTtlvDeserializer() {
    super(PingOpRequestPayload.kmipTag, PingOpRequestPayload.encodingType);
  }

  @Override
  protected PingOpRequestPayload.PingOpRequestPayloadBuilder createBuilder() {
    return PingOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PingOpRequestPayload.PingOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PingOpRequestPayload build(PingOpRequestPayload.PingOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}