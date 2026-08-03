package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;

public class LogoutOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<LogoutOpRequestPayload,
        LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder> {

  public LogoutOpRequestPayloadJsonDeserializer() {
    super(LogoutOpRequestPayload.kmipTag, LogoutOpRequestPayload.encodingType);
  }

  @Override
  protected LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder createBuilder() {
    return LogoutOpRequestPayload.builder();
  }

  @Override
  protected void setValue(LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET -> builder.ticket(ctxt.readValue(p, Ticket.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected LogoutOpRequestPayload build(
      LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}