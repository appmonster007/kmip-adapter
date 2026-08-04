package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PreviousLink Ttlv Serialization Tests")
class PreviousLinkTtlvTest extends AbstractTtlvSerializationTestSuite<PreviousLink> {

  @Override
  public Class<PreviousLink> type() {
    return PreviousLink.class;
  }

  @Override
  public PreviousLink createDefault() {
    return PreviousLink.of("test-id");
  }

  @Override
  public PreviousLink createVariant() {
    return PreviousLink.of("test-id");
  }
}