package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.X509CertificateIssuer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

public class X509CertificateIssuerBenchmarkSubject
    extends KmipBenchmarkSubject<X509CertificateIssuer> {

  public X509CertificateIssuerBenchmarkSubject() throws Exception {
    X509CertificateIssuer x509certificateissuer = X509CertificateIssuer
        .builder()
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

}