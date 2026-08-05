package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.X509CertificateSubject;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * Benchmark subject for {@link X509CertificateSubject}.
 */
public class X509CertificateSubjectBenchmarkSubject
    extends KmipBenchmarkSubject<X509CertificateSubject> {

  /**
   * Constructs a new {@link X509CertificateSubjectBenchmarkSubject}.
   */
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