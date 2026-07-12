package org.purpleBean.kmip.codec.json.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.DelegatedLoginOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

@DisplayName("DelegatedLoginOpResponsePayload Json Serialization Tests")
class DelegatedLoginOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<DelegatedLoginOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<DelegatedLoginOpResponsePayload> type() {
        return DelegatedLoginOpResponsePayload.class;
    }

    @Override
    public DelegatedLoginOpResponsePayload createDefault() {
        return DelegatedLoginOpResponsePayload.builder()
                .ticket(Ticket.builder()
                        .ticketType(TicketType.Standard.LOGIN.inst())
                        .ticketValue(TicketValue.of(new byte[]{0x01, 0x02, 0x03}))
                        .build())
                .build();
    }

    @Override
    public DelegatedLoginOpResponsePayload createVariant() {
        return DelegatedLoginOpResponsePayload.builder()
                .ticket(Ticket.builder()
                        .ticketType(TicketType.Standard.LOGIN.inst())
                        .ticketValue(TicketValue.of(new byte[]{0x04, 0x05, 0x06, 0x07}))
                        .build())
                .build();
    }
}