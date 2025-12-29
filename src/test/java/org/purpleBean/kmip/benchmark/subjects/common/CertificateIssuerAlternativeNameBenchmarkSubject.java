package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;

public class CertificateIssuerAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerAlternativeName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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