package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectCn;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectCn Xml Serialization Tests")
class CertificateSubjectCnXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectCn> {

  @Override
  public Class<CertificateSubjectCn> type() {
    return CertificateSubjectCn.class;
  }

  @Override
  public CertificateSubjectCn createDefault() {
    return CertificateSubjectCn.of("default-string");
  }

  @Override
  public CertificateSubjectCn createVariant() {
    return CertificateSubjectCn.of("variant-string");
  }
}