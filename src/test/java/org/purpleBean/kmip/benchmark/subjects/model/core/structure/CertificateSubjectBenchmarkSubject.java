package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.model.core.structure.CertificateSubject;

public class CertificateSubjectBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubject> {

    public CertificateSubjectBenchmarkSubject() throws Exception {
        CertificateSubject certificateSubject = CertificateSubject.builder()
                .certificateSubjectDistinguishedName(
                        CertificateSubjectDistinguishedName.of("CN=Test Subject")
                )
                .certificateSubjectAlternativeName(
                        CertificateSubjectAlternativeName.of("alt.subject.com")
                )
                .build();
        initialize(certificateSubject, CertificateSubject.class);
    }

    @Override
    public String name() {
        return "CertificateSubject";
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