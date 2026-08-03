package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ReplacedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReplacedObjectLink Ttlv Serialization Tests")
class ReplacedObjectLinkTtlvTest extends AbstractTtlvSerializationTestSuite<ReplacedObjectLink> {

  @Override
  public Class<ReplacedObjectLink> type() {
    return ReplacedObjectLink.class;
  }

  @Override
  public ReplacedObjectLink createDefault() {
    return ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ReplacedObjectLink createVariant() {
    return ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}