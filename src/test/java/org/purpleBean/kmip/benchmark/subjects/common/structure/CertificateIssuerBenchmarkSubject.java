package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateIssuer;

public class CertificateIssuerBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuer> {

    public CertificateIssuerBenchmarkSubject() throws Exception {
        CertificateIssuer certificateIssuer = CertificateIssuer.builder()
                .certificateIssuerDistinguishedName(
                        CertificateIssuerDistinguishedName.of("CN=Test Issuer")
                )
                .certificateIssuerAlternativeName(
                        CertificateIssuerAlternativeName.of("alt.issuer.com")
                )
                .build();
        initialize(certificateIssuer, CertificateIssuer.class);
    }

    @Override
    public String name() {
        return "CertificateIssuer";
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