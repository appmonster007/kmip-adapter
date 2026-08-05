package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerSt;

/**
 * Benchmark subject for {@link CertificateIssuerSt}.
 */
public class CertificateIssuerStBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerSt> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerStBenchmarkSubject}.
   */
  public CertificateIssuerStBenchmarkSubject() throws Exception {
    CertificateIssuerSt subject = CertificateIssuerSt.of("default-string");
    initialize(subject, CertificateIssuerSt.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerSt";
  }
}