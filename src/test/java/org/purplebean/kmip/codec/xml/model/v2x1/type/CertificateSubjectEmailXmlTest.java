package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectEmail;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectEmail Xml Serialization Tests")
class CertificateSubjectEmailXmlTest
    extends AbstractXmlSerializationTestSuite<CertificateSubjectEmail> {

  @Override
  public Class<CertificateSubjectEmail> type() {
    return CertificateSubjectEmail.class;
  }

  @Override
  public CertificateSubjectEmail createDefault() {
    return CertificateSubjectEmail.of("default-string");
  }

  @Override
  public CertificateSubjectEmail createVariant() {
    return CertificateSubjectEmail.of("variant-string");
  }
}