package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectOu;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectOu Xml Serialization Tests")
class CertificateSubjectOuXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectOu> {

  @Override
  public Class<CertificateSubjectOu> type() {
    return CertificateSubjectOu.class;
  }

  @Override
  public CertificateSubjectOu createDefault() {
    return CertificateSubjectOu.of("default-string");
  }

  @Override
  public CertificateSubjectOu createVariant() {
    return CertificateSubjectOu.of("variant-string");
  }
}