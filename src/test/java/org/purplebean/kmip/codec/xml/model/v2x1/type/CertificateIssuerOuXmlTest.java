package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerOu;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerOu Xml Serialization Tests")
class CertificateIssuerOuXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerOu> {

  @Override
  public Class<CertificateIssuerOu> type() {
    return CertificateIssuerOu.class;
  }

  @Override
  public CertificateIssuerOu createDefault() {
    return CertificateIssuerOu.of("default-string");
  }

  @Override
  public CertificateIssuerOu createVariant() {
    return CertificateIssuerOu.of("variant-string");
  }
}