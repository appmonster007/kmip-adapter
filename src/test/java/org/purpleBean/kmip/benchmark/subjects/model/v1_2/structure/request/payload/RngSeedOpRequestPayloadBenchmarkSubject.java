package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngSeedOpRequestPayload;

public class RngSeedOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<RngSeedOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public RngSeedOpRequestPayloadBenchmarkSubject() throws Exception {
        RngSeedOpRequestPayload subject = RngSeedOpRequestPayload.builder()
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
        initialize(subject, RngSeedOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "RngSeedOpRequestPayload";
    }
}
