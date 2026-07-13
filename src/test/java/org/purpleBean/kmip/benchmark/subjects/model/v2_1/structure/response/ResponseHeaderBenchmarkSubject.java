package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseHeader;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<ResponseHeader> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public ResponseHeaderBenchmarkSubject() throws Exception {
        KmipContext.setSpec(getSpec());
        ResponseHeader subject = ResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(2, 1))
                .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
                .batchCount(BatchCount.of(1))
                .build();
        initialize(subject, ResponseHeader.class);
        KmipContext.clear();
    }

    @Override
    public String name() {
        return "ResponseHeader";
    }
}
