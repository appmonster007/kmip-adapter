package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

public class TicketValueBenchmarkSubject extends KmipBenchmarkSubject<TicketValue> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

    public TicketValueBenchmarkSubject() throws Exception {
        TicketValue subject = TicketValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
        initialize(subject, TicketValue.class);
    }

    @Override
    public String name() {
        return "TicketValue";
    }
}