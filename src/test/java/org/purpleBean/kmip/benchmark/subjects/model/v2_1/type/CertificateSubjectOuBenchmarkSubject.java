package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectOu;

public class CertificateSubjectOuBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectOu> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectOuBenchmarkSubject() throws Exception {
    CertificateSubjectOu subject = CertificateSubjectOu.of("default-string");
    initialize(subject, CertificateSubjectOu.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectOu";
  }
}