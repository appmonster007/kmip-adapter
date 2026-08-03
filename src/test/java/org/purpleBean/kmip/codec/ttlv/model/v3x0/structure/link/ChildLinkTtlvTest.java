package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ChildLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ChildLink Ttlv Serialization Tests")
class ChildLinkTtlvTest extends AbstractTtlvSerializationTestSuite<ChildLink> {

  @Override
  public Class<ChildLink> type() {
    return ChildLink.class;
  }

  @Override
  public ChildLink createDefault() {
    return ChildLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ChildLink createVariant() {
    return ChildLink.of(UniqueIdentifier.of("test-id"));
  }
}