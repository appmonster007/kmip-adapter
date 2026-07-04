package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.core.enumeration.TicketType;
import org.purpleBean.kmip.model.core.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogoutOpRequestPayload Json Serialization Tests")
class LogoutOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<LogoutOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<LogoutOpRequestPayload> type() {
        return LogoutOpRequestPayload.class;
    }

    @Override
    public LogoutOpRequestPayload createDefault() {
        return LogoutOpRequestPayload.builder()
                .ticket(Ticket.builder()
                        .ticketType(TicketType.Standard.LOGIN.inst())
                        .ticketValue(TicketValue.of(new byte[]{0x01, 0x02, 0x03}))
                        .build())
                .build();
    }

    @Override
    public LogoutOpRequestPayload createVariant() {
        return LogoutOpRequestPayload.builder()
                .ticket(Ticket.builder()
                        .ticketType(TicketType.Standard.LOGIN.inst())
                        .ticketValue(TicketValue.of(new byte[]{0x04, 0x05, 0x06, 0x07}))
                        .build())
                .build();
    }
}