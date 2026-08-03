package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectEmail;

public class CertificateSubjectEmailBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectEmail> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectEmailBenchmarkSubject() throws Exception {
    CertificateSubjectEmail subject = CertificateSubjectEmail.of("default-string");
    initialize(subject, CertificateSubjectEmail.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectEmail";
  }
}