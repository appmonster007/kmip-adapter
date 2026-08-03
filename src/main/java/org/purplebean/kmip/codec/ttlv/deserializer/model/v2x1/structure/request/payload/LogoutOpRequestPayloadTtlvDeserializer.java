package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogoutOpRequestPayload;

/**
 * TTLV deserializer for {@link LogoutOpRequestPayload}.
 */
public class LogoutOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogoutOpRequestPayload,
        LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link LogoutOpRequestPayloadTtlvDeserializer}.
   */
  public LogoutOpRequestPayloadTtlvDeserializer() {
    super(LogoutOpRequestPayload.kmipTag, LogoutOpRequestPayload.encodingType);
  }

  @Override
  protected LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder createBuilder() {
    return LogoutOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(mapper.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LogoutOpRequestPayload build(
      LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}