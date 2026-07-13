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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;

public class LoginOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LoginOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public LoginOpRequestPayloadBenchmarkSubject() throws Exception {
        LoginOpRequestPayload subject = LoginOpRequestPayload.builder()
                .leaseTime(LeaseTime.of(3600))
                .requestCount(RequestCount.of(10))
                .build();
        initialize(subject, LoginOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "LoginOpRequestPayload";
    }
}