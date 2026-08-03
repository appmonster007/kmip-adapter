package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerO;

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