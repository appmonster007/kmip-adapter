package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectOu;

/**
 * Benchmark subject for {@link CertificateSubjectOu}.
 */
public class CertificateSubjectOuBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectOu> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateSubjectOuBenchmarkSubject}.
   */
  public CertificateSubjectOuBenchmarkSubject() throws Exception {
    CertificateSubjectOu subject = CertificateSubjectOu.of("default-string");
    initialize(subject, CertificateSubjectOu.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectOu";
  }
}