package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectSt;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectSt Xml Serialization Tests")
class CertificateSubjectStXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectSt> {

  @Override
  public Class<CertificateSubjectSt> type() {
    return CertificateSubjectSt.class;
  }

  @Override
  public CertificateSubjectSt createDefault() {
    return CertificateSubjectSt.of("default-string");
  }

  @Override
  public CertificateSubjectSt createVariant() {
    return CertificateSubjectSt.of("variant-string");
  }
}