package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.type.TicketValue;

public class TicketXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Ticket, Ticket.TicketBuilder> {

  public TicketXmlDeserializer() {
    super(Ticket.kmipTag, Ticket.encodingType);
  }

  @Override
  protected Ticket.TicketBuilder createBuilder() {
    return Ticket.builder();
  }

  @Override
  protected void setValue(Ticket.TicketBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.TICKET_TYPE -> builder.ticketType(ctxt.readValue(p, TicketType.class));
      case KmipTag.Standard.TICKET_VALUE ->
          builder.ticketValue(ctxt.readValue(p, TicketValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected Ticket build(Ticket.TicketBuilder builder) {
    return builder.build();
  }
}
