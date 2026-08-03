package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NextLink Ttlv Serialization Tests")
class NextLinkTtlvTest extends AbstractTtlvSerializationTestSuite<NextLink> {

  @Override
  public Class<NextLink> type() {
    return NextLink.class;
  }

  @Override
  public NextLink createDefault() {
    return NextLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public NextLink createVariant() {
    return NextLink.of(UniqueIdentifier.of("test-id"));
  }
}