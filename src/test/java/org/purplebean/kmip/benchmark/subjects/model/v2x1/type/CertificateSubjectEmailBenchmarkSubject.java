package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectEmail;

/**
 * Benchmark subject for {@link CertificateSubjectEmail}.
 */
public class CertificateSubjectEmailBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectEmail> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateSubjectEmailBenchmarkSubject}.
   */
  public CertificateSubjectEmailBenchmarkSubject() throws Exception {
    CertificateSubjectEmail subject = CertificateSubjectEmail.of("default-string");
    initialize(subject, CertificateSubjectEmail.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectEmail";
  }
}