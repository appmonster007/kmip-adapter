package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerOu;

/**
 * Benchmark subject for {@link CertificateIssuerOu}.
 */
public class CertificateIssuerOuBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerOu> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerOuBenchmarkSubject}.
   */
  public CertificateIssuerOuBenchmarkSubject() throws Exception {
    CertificateIssuerOu subject = CertificateIssuerOu.of("default-string");
    initialize(subject, CertificateIssuerOu.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerOu";
  }
}