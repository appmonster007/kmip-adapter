package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectAlternativeName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CertificateSubjectAlternativeNameBenchmarkSubject() throws Exception {
        CertificateSubjectAlternativeName certificateSubjectAlternativeName = CertificateSubjectAlternativeName.builder().value("test-subject-alt-name").build();
        initialize(certificateSubjectAlternativeName, CertificateSubjectAlternativeName.class);
    }

    @Override
    public String name() {
        return "CertificateSubjectAlternativeName";
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