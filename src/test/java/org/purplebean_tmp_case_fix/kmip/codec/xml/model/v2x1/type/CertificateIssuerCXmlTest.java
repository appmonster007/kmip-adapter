package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerC;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerC Xml Serialization Tests")
class CertificateIssuerCXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerC> {

  @Override
  public Class<CertificateIssuerC> type() {
    return CertificateIssuerC.class;
  }

  @Override
  public CertificateIssuerC createDefault() {
    return CertificateIssuerC.of("default-string");
  }

  @Override
  public CertificateIssuerC createVariant() {
    return CertificateIssuerC.of("variant-string");
  }
}