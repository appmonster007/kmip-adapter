package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.Pkcs12CertificateLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs12CertificateLink Xml Serialization Tests")
class Pkcs12CertificateLinkXmlTest
    extends AbstractXmlSerializationTestSuite<Pkcs12CertificateLink> {

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