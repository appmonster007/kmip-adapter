package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;

public class LogoutOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<LogoutOpRequestPayload,
        LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder> {

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