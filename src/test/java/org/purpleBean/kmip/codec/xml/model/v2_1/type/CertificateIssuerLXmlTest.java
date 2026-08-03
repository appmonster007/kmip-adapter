package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerL;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerL Xml Serialization Tests")
class CertificateIssuerLXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerL> {

  @Override
  public Class<CertificateIssuerL> type() {
    return CertificateIssuerL.class;
  }

  @Override
  public CertificateIssuerL createDefault() {
    return CertificateIssuerL.of("default-string");
  }

  @Override
  public CertificateIssuerL createVariant() {
    return CertificateIssuerL.of("variant-string");
  }
}