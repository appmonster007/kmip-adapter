package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.X509CertificateSubject;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("X509CertificateSubject Ttlv Serialization Tests")
class X509CertificateSubjectTtlvTest
    extends AbstractTtlvSerializationTestSuite<X509CertificateSubject> {

  @Override
  public Class<X509CertificateSubject> type() {
    return X509CertificateSubject.class;
  }

  @Override
  public X509CertificateSubject createDefault() {
    return X509CertificateSubject
        .builder()
        .subjectDistinguishedName(
            SubjectDistinguishedName.of("CN=Test Subject".getBytes())
        )
        .subjectAlternativeName(
            SubjectAlternativeName.of("alt.subject.com".getBytes())
        )
        .build();
  }
}