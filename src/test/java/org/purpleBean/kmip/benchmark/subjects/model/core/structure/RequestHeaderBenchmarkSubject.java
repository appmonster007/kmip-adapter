package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.RequestHeader;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public RequestHeaderBenchmarkSubject() throws Exception {
        RequestHeader subject = RequestHeader.builder()
                .protocolVersion(ProtocolVersion.builder()
                        .protocolVersionMajor(ProtocolVersionMajor.of(1))
                        .protocolVersionMinor(ProtocolVersionMinor.of(2))
                        .build())
                .batchCount(BatchCount.of(1))
                .build();
        initialize(subject, RequestHeader.class);
    }

    @Override
    public String name() {
        return "RequestHeader";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}