package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.LogOpResponsePayload;

public class LogOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogOpResponsePayload,
        LogOpResponsePayload.LogOpResponsePayloadBuilder> {

  public LogOpResponsePayloadTtlvDeserializer() {
    super(LogOpResponsePayload.kmipTag, LogOpResponsePayload.encodingType);
  }

  @Override
  protected LogOpResponsePayload.LogOpResponsePayloadBuilder createBuilder() {
    return LogOpResponsePayload.builder();
  }

  @Override
  protected void setValue(LogOpResponsePayload.LogOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected LogOpResponsePayload build(LogOpResponsePayload.LogOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}