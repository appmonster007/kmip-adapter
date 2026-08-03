package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.NotifyOpResponsePayload;

public class NotifyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<NotifyOpResponsePayload,
        NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder> {

  public NotifyOpResponsePayloadTtlvDeserializer() {
    super(NotifyOpResponsePayload.kmipTag, NotifyOpResponsePayload.encodingType);
  }

  @Override
  protected NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder createBuilder() {
    return NotifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    // No fields to deserialize
  }

  @Override
  protected NotifyOpResponsePayload build(
      NotifyOpResponsePayload.NotifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
