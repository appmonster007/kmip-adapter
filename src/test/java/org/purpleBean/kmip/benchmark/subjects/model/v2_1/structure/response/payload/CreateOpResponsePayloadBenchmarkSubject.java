package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.CreateOpResponsePayload;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class CreateOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<CreateOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public CreateOpResponsePayloadBenchmarkSubject() throws Exception {
        CreateOpResponsePayload subject = CreateOpResponsePayload.builder().objectType(ObjectType.Standard.SYMMETRIC_KEY.inst()).uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
        initialize(subject, CreateOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "CreateOpResponsePayload";
    }
}
