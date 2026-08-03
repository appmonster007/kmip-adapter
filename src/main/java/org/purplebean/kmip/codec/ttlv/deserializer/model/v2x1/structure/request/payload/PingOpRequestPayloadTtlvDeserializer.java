package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;

/**
 * TTLV deserializer for {@link PingOpRequestPayload}.
 */
public class PingOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PingOpRequestPayload,
        PingOpRequestPayload.PingOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link PingOpRequestPayloadTtlvDeserializer}.
   */
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