package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectC;

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