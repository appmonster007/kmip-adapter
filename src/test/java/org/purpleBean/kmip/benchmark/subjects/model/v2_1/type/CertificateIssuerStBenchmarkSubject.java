package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;

public class CertificateIssuerStBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerSt> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerStBenchmarkSubject() throws Exception {
    CertificateIssuerSt subject = CertificateIssuerSt.of("default-string");
    initialize(subject, CertificateIssuerSt.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerSt";
  }
}