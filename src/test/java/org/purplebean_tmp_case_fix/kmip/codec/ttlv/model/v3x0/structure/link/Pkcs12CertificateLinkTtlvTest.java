package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.Pkcs12CertificateLink;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs12CertificateLink Ttlv Serialization Tests")
class Pkcs12CertificateLinkTtlvTest
    extends AbstractTtlvSerializationTestSuite<Pkcs12CertificateLink> {

  @Override
  public Class<Pkcs12CertificateLink> type() {
    return Pkcs12CertificateLink.class;
  }

  @Override
  public Pkcs12CertificateLink createDefault() {
    return Pkcs12CertificateLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public Pkcs12CertificateLink createVariant() {
    return Pkcs12CertificateLink.of(UniqueIdentifier.of("test-id"));
  }
}