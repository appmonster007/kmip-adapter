package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RevokeOpResponsePayload;

public class RevokeOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<RevokeOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public RevokeOpResponsePayloadBenchmarkSubject() throws Exception {
        RevokeOpResponsePayload subject = RevokeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
        initialize(subject, RevokeOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "RevokeOpResponsePayload";
    }
}
