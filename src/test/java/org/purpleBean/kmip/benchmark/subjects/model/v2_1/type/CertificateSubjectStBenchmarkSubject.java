package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectSt;

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