package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerL;

public class CertificateIssuerLBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerL> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateIssuerLBenchmarkSubject() throws Exception {
    CertificateIssuerL subject = CertificateIssuerL.of("default-string");
    initialize(subject, CertificateIssuerL.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerL";
  }
}