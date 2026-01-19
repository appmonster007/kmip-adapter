package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.RegisterOpRequestPayload;

public class RegisterOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<RegisterOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public RegisterOpRequestPayloadBenchmarkSubject() throws Exception {
        RegisterOpRequestPayload subject = RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .templateAttribute(TemplateAttribute.builder().build())
                .object(SymmetricKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                                .build())
                        .build())
                .build();
        initialize(subject, RegisterOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "RegisterOpRequestPayload";
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