package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.TicketType;
import org.purpleBean.kmip.model.core.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Ticket Xml Serialization Tests")
class TicketXmlTest extends AbstractXmlSerializationTestSuite<Ticket> {

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
}
