package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;

public class LocateOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<LocateOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public LocateOpRequestPayloadBenchmarkSubject() throws Exception {
        LocateOpRequestPayload subject = LocateOpRequestPayload.builder().build();
        initialize(subject, LocateOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "LocateOpRequestPayload";
    }
}
