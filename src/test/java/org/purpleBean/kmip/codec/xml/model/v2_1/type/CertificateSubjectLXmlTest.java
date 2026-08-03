package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectL;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectL Xml Serialization Tests")
class CertificateSubjectLXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectL> {

  @Override
  public Class<CertificateSubjectL> type() {
    return CertificateSubjectL.class;
  }

  @Override
  public CertificateSubjectL createDefault() {
    return CertificateSubjectL.of("default-string");
  }

  @Override
  public CertificateSubjectL createVariant() {
    return CertificateSubjectL.of("variant-string");
  }
}