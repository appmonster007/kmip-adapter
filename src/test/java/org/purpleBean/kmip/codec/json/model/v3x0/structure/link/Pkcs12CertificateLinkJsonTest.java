package org.purpleBean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.Pkcs12CertificateLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs12CertificateLink Json Serialization Tests")
class Pkcs12CertificateLinkJsonTest
    extends AbstractJsonSerializationTestSuite<Pkcs12CertificateLink> {

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