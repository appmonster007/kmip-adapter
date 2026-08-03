package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.CertificateIssuer;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuer Json Serialization Tests")
class CertificateIssuerJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuer> {

  @Override
  public Class<CertificateIssuer> type() {
    return CertificateIssuer.class;
  }

  @Override
  public CertificateIssuer createDefault() {
    return CertificateIssuer
        .builder()
        .certificateIssuerDistinguishedName(
            CertificateIssuerDistinguishedName.of("CN=Test Issuer")
        )
        .certificateIssuerAlternativeName(
            CertificateIssuerAlternativeName.of("alt.issuer.com")
        )
        .build();
  }
}