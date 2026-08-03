package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.WrappingKeyLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("WrappingKeyLink Ttlv Serialization Tests")
class WrappingKeyLinkTtlvTest extends AbstractTtlvSerializationTestSuite<WrappingKeyLink> {

  @Override
  public Class<WrappingKeyLink> type() {
    return WrappingKeyLink.class;
  }

  @Override
  public WrappingKeyLink createDefault() {
    return WrappingKeyLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public WrappingKeyLink createVariant() {
    return WrappingKeyLink.of(UniqueIdentifier.of("test-id"));
  }
}