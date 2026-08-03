package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectO;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectO Xml Serialization Tests")
class CertificateSubjectOXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectO> {

  @Override
  public Class<CertificateSubjectO> type() {
    return CertificateSubjectO.class;
  }

  @Override
  public CertificateSubjectO createDefault() {
    return CertificateSubjectO.of("default-string");
  }

  @Override
  public CertificateSubjectO createVariant() {
    return CertificateSubjectO.of("variant-string");
  }
}