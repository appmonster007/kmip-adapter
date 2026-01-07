package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

public class X509CertificateIssuerBenchmarkSubject extends KmipBenchmarkSubject<X509CertificateIssuer> {

    public X509CertificateIssuerBenchmarkSubject() throws Exception {
        X509CertificateIssuer x509certificateissuer = X509CertificateIssuer.builder()
                .issuerDistinguishedName(
                        IssuerDistinguishedName.of("CN=Test Issuer".getBytes())
                )
                .issuerAlternativeName(
                        IssuerAlternativeName.of("alt.issuer.com".getBytes())
                )
                .build();
        initialize(x509certificateissuer, X509CertificateIssuer.class);
    }

    @Override
    public String name() {
        return "X509CertificateIssuer";
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