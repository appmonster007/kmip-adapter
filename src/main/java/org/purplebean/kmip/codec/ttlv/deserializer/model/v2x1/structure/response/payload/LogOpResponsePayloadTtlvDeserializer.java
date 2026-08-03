package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogOpResponsePayload;

/**
 * TTLV deserializer for {@link LogOpResponsePayload}.
 */
public class LogOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogOpResponsePayload,
        LogOpResponsePayload.LogOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link LogOpResponsePayloadTtlvDeserializer}.
   */
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