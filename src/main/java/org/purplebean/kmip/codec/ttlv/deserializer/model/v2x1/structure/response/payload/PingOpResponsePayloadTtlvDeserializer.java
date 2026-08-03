package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.payload.PingOpResponsePayload;

/**
 * TTLV deserializer for {@link PingOpResponsePayload}.
 */
public class PingOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PingOpResponsePayload,
        PingOpResponsePayload.PingOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link PingOpResponsePayloadTtlvDeserializer}.
   */
  public PingOpResponsePayloadTtlvDeserializer() {
    super(PingOpResponsePayload.kmipTag, PingOpResponsePayload.encodingType);
  }

  @Override
  protected PingOpResponsePayload.PingOpResponsePayloadBuilder createBuilder() {
    return PingOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PingOpResponsePayload.PingOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected PingOpResponsePayload build(
      PingOpResponsePayload.PingOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}