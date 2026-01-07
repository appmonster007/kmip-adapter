package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerDistinguishedName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CertificateIssuerDistinguishedNameBenchmarkSubject() throws Exception {
        CertificateIssuerDistinguishedName certificateIssuerDistinguishedName = CertificateIssuerDistinguishedName.of("CN=Test Issuer");
        initialize(certificateIssuerDistinguishedName, CertificateIssuerDistinguishedName.class);
    }

    @Override
    public String name() {
        return "CertificateIssuerDistinguishedName";
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