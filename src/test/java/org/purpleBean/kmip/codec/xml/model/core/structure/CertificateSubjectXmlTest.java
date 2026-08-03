package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.CertificateSubject;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubject Xml Serialization Tests")
class CertificateSubjectXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubject> {

  @Override
  public Class<CertificateSubject> type() {
    return CertificateSubject.class;
  }

  @Override
  public CertificateSubject createDefault() {
    return CertificateSubject
        .builder()
        .certificateSubjectDistinguishedName(
            CertificateSubjectDistinguishedName.of("CN=Test Subject")
        )
        .certificateSubjectAlternativeName(
            CertificateSubjectAlternativeName.of("alt.subject.com")
        )
        .build();
  }
}