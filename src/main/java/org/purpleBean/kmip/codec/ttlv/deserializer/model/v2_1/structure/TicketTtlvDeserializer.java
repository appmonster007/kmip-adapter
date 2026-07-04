package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.TicketType;
import org.purpleBean.kmip.model.core.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TicketTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Ticket, Ticket.TicketBuilder> {

    public TicketTtlvDeserializer() {
        super(Ticket.kmipTag, Ticket.encodingType);
    }

    @Override
    protected Ticket.TicketBuilder createBuilder() {
        return Ticket.builder();
    }

    @Override
    protected void setValue(Ticket.TicketBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.TICKET_TYPE -> builder.ticketType(mapper.readValue(p, TicketType.class));
            case KmipTag.Standard.TICKET_VALUE -> builder.ticketValue(mapper.readValue(p, TicketValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Ticket build(Ticket.TicketBuilder builder) {
        return builder.build();
    }
}
