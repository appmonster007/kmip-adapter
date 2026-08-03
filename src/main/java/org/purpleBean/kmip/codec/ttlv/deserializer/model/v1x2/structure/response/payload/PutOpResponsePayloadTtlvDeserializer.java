package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;

public class PutOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PutOpResponsePayload,
        PutOpResponsePayload.PutOpResponsePayloadBuilder> {

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
