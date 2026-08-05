package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerC;

/**
 * Benchmark subject for {@link CertificateIssuerC}.
 */
public class CertificateIssuerCBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerC> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerCBenchmarkSubject}.
   */
  public CertificateIssuerCBenchmarkSubject() throws Exception {
    CertificateIssuerC subject = CertificateIssuerC.of("default-string");
    initialize(subject, CertificateIssuerC.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerC";
  }
}