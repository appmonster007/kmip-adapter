package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.CertificateIssuer;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuer> {

  public CertificateIssuerBenchmarkSubject() throws Exception {
    CertificateIssuer certificateIssuer = CertificateIssuer
        .builder()
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

}