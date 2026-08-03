package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateSubjectAlternativeName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectAlternativeName XML Serialization Tests")
class CertificateSubjectAlternativeNameXmlTest
    extends AbstractXmlSerializationTestSuite<CertificateSubjectAlternativeName> {

  @Override
  public Class<CertificateSubjectAlternativeName> type() {
    return CertificateSubjectAlternativeName.class;
  }

  @Override
  public CertificateSubjectAlternativeName createDefault() {
    return CertificateSubjectAlternativeName
        .builder()
        .value("test-subject-alt-name")
        .build();
  }

  @Override
  public CertificateSubjectAlternativeName createVariant() {
    return CertificateSubjectAlternativeName
        .builder()
        .value("another-subject-alt-name")
        .build();
  }
}