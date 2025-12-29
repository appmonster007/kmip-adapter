package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;

public class CertificateSubjectDistinguishedNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectDistinguishedName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CertificateSubjectDistinguishedNameBenchmarkSubject() throws Exception {
        CertificateSubjectDistinguishedName certificateSubjectDistinguishedName = CertificateSubjectDistinguishedName.builder().value("test-subject-dn").build();
        initialize(certificateSubjectDistinguishedName, CertificateSubjectDistinguishedName.class);
    }

    @Override
    public String name() {
        return "CertificateSubjectDistinguishedName";
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