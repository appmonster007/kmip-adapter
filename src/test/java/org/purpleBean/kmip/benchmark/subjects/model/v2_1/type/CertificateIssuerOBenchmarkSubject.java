package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerO;

public class CertificateIssuerOBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerO> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerOBenchmarkSubject() throws Exception {
    CertificateIssuerO subject = CertificateIssuerO.of("default-string");
    initialize(subject, CertificateIssuerO.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerO";
  }
}