package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectO;

/**
 * Benchmark subject for {@link CertificateSubjectO}.
 */
public class CertificateSubjectOBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectO> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateSubjectOBenchmarkSubject}.
   */
  public CertificateSubjectOBenchmarkSubject() throws Exception {
    CertificateSubjectO subject = CertificateSubjectO.of("default-string");
    initialize(subject, CertificateSubjectO.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectO";
  }
}