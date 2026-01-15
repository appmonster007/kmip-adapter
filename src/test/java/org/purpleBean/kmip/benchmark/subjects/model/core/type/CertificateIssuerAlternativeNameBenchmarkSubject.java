package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerAlternativeName> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CertificateIssuerAlternativeNameBenchmarkSubject() throws Exception {
        CertificateIssuerAlternativeName certificateIssuerAlternativeName = CertificateIssuerAlternativeName.builder().value("test-issuer-alt-name").build();
        initialize(certificateIssuerAlternativeName, CertificateIssuerAlternativeName.class);
    }

    @Override
    public String name() {
        return "CertificateIssuerAlternativeName";
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