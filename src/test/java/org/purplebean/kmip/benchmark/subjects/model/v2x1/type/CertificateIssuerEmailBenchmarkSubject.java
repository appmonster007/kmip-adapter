package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerEmail;

/**
 * Benchmark subject for {@link CertificateIssuerEmail}.
 */
public class CertificateIssuerEmailBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIssuerEmail> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerEmailBenchmarkSubject}.
   */
  public CertificateIssuerEmailBenchmarkSubject() throws Exception {
    CertificateIssuerEmail subject = CertificateIssuerEmail.of("default-string");
    initialize(subject, CertificateIssuerEmail.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerEmail";
  }
}