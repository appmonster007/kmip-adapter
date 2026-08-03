package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateSubjectAlternativeName Domain Tests")
class CertificateSubjectAlternativeNameTest
    extends AbstractKmipDataTypeTestSuite<CertificateSubjectAlternativeName> {

  @Override
  protected Class<CertificateSubjectAlternativeName> type() {
    return CertificateSubjectAlternativeName.class;
  }

  @Override
  protected CertificateSubjectAlternativeName createDefault() {
    return CertificateSubjectAlternativeName
        .builder()
        .value("test-subject-alt-name")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}