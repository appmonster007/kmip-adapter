package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerCn;

public class CertificateIssuerCnBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerCn> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerCnBenchmarkSubject() throws Exception {
    CertificateIssuerCn subject = CertificateIssuerCn.of("default-string");
    initialize(subject, CertificateIssuerCn.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerCn";
  }
}