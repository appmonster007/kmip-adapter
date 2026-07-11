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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogOpRequestPayload;

public class LogOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LogOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public LogOpRequestPayloadBenchmarkSubject() throws Exception {
        LogOpRequestPayload subject = LogOpRequestPayload.builder()
                .logMessage(LogMessage.builder().value("test-log-message").build())
                .build();
        initialize(subject, LogOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "LogOpRequestPayload";
    }
}