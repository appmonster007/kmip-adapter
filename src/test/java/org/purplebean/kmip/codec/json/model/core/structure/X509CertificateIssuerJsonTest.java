package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.X509CertificateIssuer;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("X509CertificateIssuer Json Serialization Tests")
class X509CertificateIssuerJsonTest
    extends AbstractJsonSerializationTestSuite<X509CertificateIssuer> {

  @Override
  public Class<X509CertificateIssuer> type() {
    return X509CertificateIssuer.class;
  }

  @Override
  public X509CertificateIssuer createDefault() {
    return X509CertificateIssuer
        .builder()
        .issuerDistinguishedName(
            IssuerDistinguishedName.of("CN=Test Issuer".getBytes())
        )
        .issuerAlternativeName(
            IssuerAlternativeName.of("alt.issuer.com".getBytes())
        )
        .build();
  }
}