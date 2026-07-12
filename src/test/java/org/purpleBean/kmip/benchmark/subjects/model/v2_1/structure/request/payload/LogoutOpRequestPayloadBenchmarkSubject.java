package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;

public class LogoutOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LogoutOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public LogoutOpRequestPayloadBenchmarkSubject() throws Exception {
        LogoutOpRequestPayload subject = LogoutOpRequestPayload.builder()
                .ticket(Ticket.builder()
                        .ticketType(TicketType.Standard.LOGIN.inst())
                        .ticketValue(TicketValue.of(new byte[]{0x01, 0x02, 0x03}))
                        .build())
                .build();
        initialize(subject, LogoutOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "LogoutOpRequestPayload";
    }
}