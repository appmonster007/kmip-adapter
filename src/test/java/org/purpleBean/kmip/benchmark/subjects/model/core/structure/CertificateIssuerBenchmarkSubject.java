package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CertificateIssuer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

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