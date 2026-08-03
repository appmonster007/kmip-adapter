package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.CertificateLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateLink Xml Serialization Tests")
class CertificateLinkXmlTest extends AbstractXmlSerializationTestSuite<CertificateLink> {

  @Override
  public Class<CertificateLink> type() {
    return CertificateLink.class;
  }

  @Override
  public CertificateLink createDefault() {
    return CertificateLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public CertificateLink createVariant() {
    return CertificateLink.of(UniqueIdentifier.of("test-id"));
  }
}