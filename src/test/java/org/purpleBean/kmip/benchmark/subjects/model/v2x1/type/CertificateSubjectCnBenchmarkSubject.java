package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectCn;

public class CertificateSubjectCnBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectCn> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectCnBenchmarkSubject() throws Exception {
    CertificateSubjectCn subject = CertificateSubjectCn.of("default-string");
    initialize(subject, CertificateSubjectCn.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectCn";
  }
}