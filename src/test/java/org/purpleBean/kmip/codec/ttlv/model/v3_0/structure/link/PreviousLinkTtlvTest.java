package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.PreviousLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PreviousLink Ttlv Serialization Tests")
class PreviousLinkTtlvTest extends AbstractTtlvSerializationTestSuite<PreviousLink> {

  @Override
  public Class<PreviousLink> type() {
    return PreviousLink.class;
  }

  @Override
  public PreviousLink createDefault() {
    return PreviousLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PreviousLink createVariant() {
    return PreviousLink.of(UniqueIdentifier.of("test-id"));
  }
}