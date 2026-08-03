package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;

/**
 * TTLV deserializer for {@link PutOpResponsePayload}.
 */
public class PutOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PutOpResponsePayload,
        PutOpResponsePayload.PutOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link PutOpResponsePayloadTtlvDeserializer}.
   */
  public PutOpResponsePayloadTtlvDeserializer() {
    super(PutOpResponsePayload.kmipTag, PutOpResponsePayload.encodingType);
  }

  @Override
  protected PutOpResponsePayload.PutOpResponsePayloadBuilder createBuilder() {
    return PutOpResponsePayload.builder();
  }

  @Override
  protected void setValue(PutOpResponsePayload.PutOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields to deserialize
  }

  @Override
  protected PutOpResponsePayload build(PutOpResponsePayload.PutOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
