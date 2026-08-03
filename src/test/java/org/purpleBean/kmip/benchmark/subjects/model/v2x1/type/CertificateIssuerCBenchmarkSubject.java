package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerC;

public class CertificateIssuerCBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerC> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerCBenchmarkSubject() throws Exception {
    CertificateIssuerC subject = CertificateIssuerC.of("default-string");
    initialize(subject, CertificateIssuerC.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerC";
  }
}