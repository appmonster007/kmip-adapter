package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetOpResponsePayload;

public class GetOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<GetOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public GetOpResponsePayloadBenchmarkSubject() throws Exception {
        GetOpResponsePayload subject = GetOpResponsePayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
        initialize(subject, GetOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "GetOpResponsePayload";
    }
}
