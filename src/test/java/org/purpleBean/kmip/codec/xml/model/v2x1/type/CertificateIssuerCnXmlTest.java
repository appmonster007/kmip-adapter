package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerCn;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerCn Xml Serialization Tests")
class CertificateIssuerCnXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerCn> {

  @Override
  public Class<CertificateIssuerCn> type() {
    return CertificateIssuerCn.class;
  }

  @Override
  public CertificateIssuerCn createDefault() {
    return CertificateIssuerCn.of("default-string");
  }

  @Override
  public CertificateIssuerCn createVariant() {
    return CertificateIssuerCn.of("variant-string");
  }
}