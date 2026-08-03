package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.request;

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
import org.purpleBean.kmip.model.v3_0.structure.request.RequestHeader;

public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public RequestHeaderBenchmarkSubject() throws Exception {
        RequestHeader subject = RequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(3, 0))
                .build();
        initialize(subject, RequestHeader.class);
    }

    @Override
    public String name() {
        return "RequestHeader";
    }
}