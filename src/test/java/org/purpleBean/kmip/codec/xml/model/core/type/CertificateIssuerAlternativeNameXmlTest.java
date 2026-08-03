package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerAlternativeName XML Serialization Tests")
class CertificateIssuerAlternativeNameXmlTest
    extends AbstractXmlSerializationTestSuite<CertificateIssuerAlternativeName> {

  @Override
  public Class<CertificateIssuerAlternativeName> type() {
    return CertificateIssuerAlternativeName.class;
  }

  @Override
  public CertificateIssuerAlternativeName createDefault() {
    return CertificateIssuerAlternativeName
        .builder()
        .value("test-issuer-alt-name")
        .build();
  }

  @Override
  public CertificateIssuerAlternativeName createVariant() {
    return CertificateIssuerAlternativeName
        .builder()
        .value("another-issuer-alt-name")
        .build();
  }
}