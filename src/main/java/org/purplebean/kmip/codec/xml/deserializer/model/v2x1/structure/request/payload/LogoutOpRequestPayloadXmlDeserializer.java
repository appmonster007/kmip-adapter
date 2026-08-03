package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogoutOpRequestPayload;

public class LogoutOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<LogoutOpRequestPayload,
        LogoutOpRequestPayload.LogoutOpRequestPayloadBuilder> {

  public LogoutOpRequestPayloadXmlDeserializer() {
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