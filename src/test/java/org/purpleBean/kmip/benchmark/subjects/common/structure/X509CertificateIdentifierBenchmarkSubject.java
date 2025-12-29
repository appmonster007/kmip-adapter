package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.CertificateSerialNumber;

public class X509CertificateIdentifierBenchmarkSubject extends KmipBenchmarkSubject<X509CertificateIdentifier> {

    public X509CertificateIdentifierBenchmarkSubject() throws Exception {
        X509CertificateIdentifier x509CertificateIdentifier = X509CertificateIdentifier.builder()
                .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer".getBytes()))
                .certificateSerialNumber(CertificateSerialNumber.of("12345".getBytes()))
                .build();
        initialize(x509CertificateIdentifier, X509CertificateIdentifier.class);
    }

    @Override
    public String name() {
        return "X509CertificateIdentifier";
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
