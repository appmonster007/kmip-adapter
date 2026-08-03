package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateSubjectAlternativeName;

public class CertificateSubjectAlternativeNameBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectAlternativeName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CertificateSubjectAlternativeNameBenchmarkSubject() throws Exception {
    CertificateSubjectAlternativeName certificateSubjectAlternativeName =
        CertificateSubjectAlternativeName
            .builder()
            .value("test-subject-alt-name")
            .build();
    initialize(certificateSubjectAlternativeName, CertificateSubjectAlternativeName.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectAlternativeName";
  }

}