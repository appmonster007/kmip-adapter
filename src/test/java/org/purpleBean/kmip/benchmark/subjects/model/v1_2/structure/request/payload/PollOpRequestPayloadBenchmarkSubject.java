package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;

public class PollOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<PollOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public PollOpRequestPayloadBenchmarkSubject() throws Exception {
        PollOpRequestPayload subject = PollOpRequestPayload.builder()
                .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[]{1, 2, 3}))
                .build();
        initialize(subject, PollOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "PollOpRequestPayload";
    }
}
