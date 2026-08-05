package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.X509CertificateIssuer;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * Benchmark subject for {@link X509CertificateIssuer}.
 */
public class X509CertificateIssuerBenchmarkSubject
    extends KmipBenchmarkSubject<X509CertificateIssuer> {

  /**
   * Constructs a new {@link X509CertificateIssuerBenchmarkSubject}.
   */
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