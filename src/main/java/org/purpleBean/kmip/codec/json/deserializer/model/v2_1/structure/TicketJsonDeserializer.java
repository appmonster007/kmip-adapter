package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;

import java.io.IOException;

public class TicketJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Ticket, Ticket.TicketBuilder> {

    public TicketJsonDeserializer() {
        super(Ticket.kmipTag, Ticket.encodingType);
    }

    @Override
    protected Ticket.TicketBuilder createBuilder() {
        return Ticket.builder();
    }

    @Override
    protected void setValue(Ticket.TicketBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.TICKET_TYPE -> builder.ticketType(ctxt.readValue(p, TicketType.class));
            case KmipTag.Standard.TICKET_VALUE -> builder.ticketValue(ctxt.readValue(p, TicketValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Ticket build(Ticket.TicketBuilder builder) {
        return builder.build();
    }
}
