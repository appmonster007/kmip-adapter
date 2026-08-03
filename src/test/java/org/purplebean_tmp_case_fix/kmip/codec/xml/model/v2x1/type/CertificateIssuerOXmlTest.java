package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerO;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerO Xml Serialization Tests")
class CertificateIssuerOXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerO> {

  @Override
  public Class<CertificateIssuerO> type() {
    return CertificateIssuerO.class;
  }

  @Override
  public CertificateIssuerO createDefault() {
    return CertificateIssuerO.of("default-string");
  }

  @Override
  public CertificateIssuerO createVariant() {
    return CertificateIssuerO.of("variant-string");
  }
}