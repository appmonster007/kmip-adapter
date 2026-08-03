package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerSt;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerSt Xml Serialization Tests")
class CertificateIssuerStXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerSt> {

  @Override
  public Class<CertificateIssuerSt> type() {
    return CertificateIssuerSt.class;
  }

  @Override
  public CertificateIssuerSt createDefault() {
    return CertificateIssuerSt.of("default-string");
  }

  @Override
  public CertificateIssuerSt createVariant() {
    return CertificateIssuerSt.of("variant-string");
  }
}