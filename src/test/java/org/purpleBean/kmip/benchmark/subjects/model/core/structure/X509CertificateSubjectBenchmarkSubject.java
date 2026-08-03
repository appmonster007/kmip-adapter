package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.X509CertificateSubject;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

public class X509CertificateSubjectBenchmarkSubject
    extends KmipBenchmarkSubject<X509CertificateSubject> {

  public X509CertificateSubjectBenchmarkSubject() throws Exception {
    X509CertificateSubject x509certificatesubject = X509CertificateSubject
        .builder()
        .subjectDistinguishedName(
            SubjectDistinguishedName.of("CN=Test Subject".getBytes())
        )
        .subjectAlternativeName(
            SubjectAlternativeName.of("alt.subject.com".getBytes())
        )
        .build();
    initialize(x509certificatesubject, X509CertificateSubject.class);
  }

  @Override
  public String name() {
    return "X509CertificateSubject";
  }

}