package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerEmail;

public class CertificateIssuerEmailBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIssuerEmail> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerEmailBenchmarkSubject() throws Exception {
    CertificateIssuerEmail subject = CertificateIssuerEmail.of("default-string");
    initialize(subject, CertificateIssuerEmail.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerEmail";
  }
}