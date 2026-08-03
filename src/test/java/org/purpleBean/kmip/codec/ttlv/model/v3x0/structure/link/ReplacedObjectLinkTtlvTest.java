package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacedObjectLink;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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