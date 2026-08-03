package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.PublicKeyLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicKeyLink Ttlv Serialization Tests")
class PublicKeyLinkTtlvTest extends AbstractTtlvSerializationTestSuite<PublicKeyLink> {

  @Override
  public Class<PublicKeyLink> type() {
    return PublicKeyLink.class;
  }

  @Override
  public PublicKeyLink createDefault() {
    return PublicKeyLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PublicKeyLink createVariant() {
    return PublicKeyLink.of(UniqueIdentifier.of("test-id"));
  }
}