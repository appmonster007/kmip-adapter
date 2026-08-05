package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectCn;

/**
 * Benchmark subject for {@link CertificateSubjectCn}.
 */
public class CertificateSubjectCnBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectCn> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateSubjectCnBenchmarkSubject}.
   */
  public CertificateSubjectCnBenchmarkSubject() throws Exception {
    CertificateSubjectCn subject = CertificateSubjectCn.of("default-string");
    initialize(subject, CertificateSubjectCn.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectCn";
  }
}