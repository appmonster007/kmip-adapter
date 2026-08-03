package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.PrivateKeyLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PrivateKeyLink Ttlv Serialization Tests")
class PrivateKeyLinkTtlvTest extends AbstractTtlvSerializationTestSuite<PrivateKeyLink> {

  @Override
  public Class<PrivateKeyLink> type() {
    return PrivateKeyLink.class;
  }

  @Override
  public PrivateKeyLink createDefault() {
    return PrivateKeyLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PrivateKeyLink createVariant() {
    return PrivateKeyLink.of(UniqueIdentifier.of("test-id"));
  }
}