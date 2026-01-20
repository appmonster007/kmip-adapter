package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;

public class CreateOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<CreateOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public CreateOpRequestPayloadBenchmarkSubject() throws Exception {
        CreateOpRequestPayload subject = CreateOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
        initialize(subject, CreateOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "CreateOpRequestPayload";
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