package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectO;

public class CertificateSubjectOBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectO> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectOBenchmarkSubject() throws Exception {
    CertificateSubjectO subject = CertificateSubjectO.of("default-string");
    initialize(subject, CertificateSubjectO.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectO";
  }
}