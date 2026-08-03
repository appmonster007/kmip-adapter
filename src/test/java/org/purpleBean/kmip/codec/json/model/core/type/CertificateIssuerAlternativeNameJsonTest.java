package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerAlternativeName JSON Serialization Tests")
class CertificateIssuerAlternativeNameJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateIssuerAlternativeName> {

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