package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectC;

public class CertificateSubjectCBenchmarkSubject extends KmipBenchmarkSubject<CertificateSubjectC> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectCBenchmarkSubject() throws Exception {
    CertificateSubjectC subject = CertificateSubjectC.of("default-string");
    initialize(subject, CertificateSubjectC.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectC";
  }
}