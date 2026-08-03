package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogoutOpResponsePayload;

public class LogoutOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogoutOpResponsePayload,
        LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder> {

  public LogoutOpResponsePayloadTtlvDeserializer() {
    super(LogoutOpResponsePayload.kmipTag, LogoutOpResponsePayload.encodingType);
  }

  @Override
  protected LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder createBuilder() {
    return LogoutOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected LogoutOpResponsePayload build(
      LogoutOpResponsePayload.LogoutOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}