package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CertifyOpResponsePayload;

public class CertifyOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<CertifyOpResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public CertifyOpResponsePayloadBenchmarkSubject() throws Exception {
        CertifyOpResponsePayload subject = CertifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
                .build();
        initialize(subject, CertifyOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "CertifyOpResponsePayload";
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