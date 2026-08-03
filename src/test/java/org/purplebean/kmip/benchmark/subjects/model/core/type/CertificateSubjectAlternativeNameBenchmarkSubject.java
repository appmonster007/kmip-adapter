package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;

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