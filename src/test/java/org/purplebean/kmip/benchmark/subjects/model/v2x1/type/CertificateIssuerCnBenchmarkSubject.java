package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerCn;

/**
 * Benchmark subject for {@link CertificateIssuerCn}.
 */
public class CertificateIssuerCnBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerCn> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerCnBenchmarkSubject}.
   */
  public CertificateIssuerCnBenchmarkSubject() throws Exception {
    CertificateIssuerCn subject = CertificateIssuerCn.of("default-string");
    initialize(subject, CertificateIssuerCn.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerCn";
  }
}