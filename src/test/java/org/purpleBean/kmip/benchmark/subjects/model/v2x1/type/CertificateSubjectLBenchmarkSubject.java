package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectL;

public class CertificateSubjectLBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectL> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectLBenchmarkSubject() throws Exception {
    CertificateSubjectL subject = CertificateSubjectL.of("default-string");
    initialize(subject, CertificateSubjectL.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectL";
  }
}