package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.CertificateIssuer;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuer Ttlv Serialization Tests")
class CertificateIssuerTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuer> {

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