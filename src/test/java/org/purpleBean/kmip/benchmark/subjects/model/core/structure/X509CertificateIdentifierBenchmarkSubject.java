package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

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

}
