package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetOpRequestPayload;

public class GetOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<GetOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public GetOpRequestPayloadBenchmarkSubject() throws Exception {
        GetOpRequestPayload subject = GetOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                .build();
        initialize(subject, GetOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "GetOpRequestPayload";
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