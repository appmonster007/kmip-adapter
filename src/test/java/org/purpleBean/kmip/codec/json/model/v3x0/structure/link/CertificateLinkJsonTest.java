package org.purpleBean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.CertificateLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateLink Json Serialization Tests")
class CertificateLinkJsonTest extends AbstractJsonSerializationTestSuite<CertificateLink> {

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