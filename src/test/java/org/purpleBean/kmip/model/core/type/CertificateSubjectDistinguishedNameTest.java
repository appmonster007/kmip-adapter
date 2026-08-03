package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateSubjectDistinguishedName Domain Tests")
class CertificateSubjectDistinguishedNameTest
    extends AbstractKmipDataTypeTestSuite<CertificateSubjectDistinguishedName> {

  @Override
  protected Class<CertificateSubjectDistinguishedName> type() {
    return CertificateSubjectDistinguishedName.class;
  }

  @Override
  protected CertificateSubjectDistinguishedName createDefault() {
    return CertificateSubjectDistinguishedName
        .builder()
        .value("test-subject-dn")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}