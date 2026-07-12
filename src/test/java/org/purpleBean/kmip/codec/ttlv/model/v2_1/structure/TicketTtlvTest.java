package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Ticket Ttlv Serialization Tests")
class TicketTtlvTest extends AbstractTtlvSerializationTestSuite<Ticket> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<Ticket> type() {
        return Ticket.class;
    }

    @Override
    public Ticket createDefault() {
        return Ticket.builder()
                .ticketType(TicketType.Standard.LOGIN.inst())
                .ticketValue(TicketValue.of(new byte[]{0x01, 0x02, 0x03}))
                .build();
    }

    @Override
    public Ticket createVariant() {
        return Ticket.builder()
                .ticketType(TicketType.Standard.LOGIN.inst())
                .ticketValue(TicketValue.of(new byte[]{0x04, 0x05, 0x06, 0x07}))
                .build();
    }

    @Test
    @DisplayName("Ticket: null TicketType throws NullPointerException")
    void ticket_nullTicketType_throws() {
        assertThatThrownBy(() -> Ticket.builder()
                .ticketType(null)
                .ticketValue(TicketValue.of(new byte[]{0x01}))
                .build())
                .isInstanceOf(NullPointerException.class);
    }
}
