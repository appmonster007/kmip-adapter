package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateIssuerAlternativeName Domain Tests")
class CertificateIssuerAlternativeNameTest
    extends AbstractKmipDataTypeTestSuite<CertificateIssuerAlternativeName> {

  @Override
  protected Class<CertificateIssuerAlternativeName> type() {
    return CertificateIssuerAlternativeName.class;
  }

  @Override
  protected CertificateIssuerAlternativeName createDefault() {
    return CertificateIssuerAlternativeName
        .builder()
        .value("test-issuer-alt-name")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}