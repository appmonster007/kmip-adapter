package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectSt;

public class CertificateSubjectStBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectSt> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectStBenchmarkSubject() throws Exception {
    CertificateSubjectSt subject = CertificateSubjectSt.of("default-string");
    initialize(subject, CertificateSubjectSt.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectSt";
  }
}