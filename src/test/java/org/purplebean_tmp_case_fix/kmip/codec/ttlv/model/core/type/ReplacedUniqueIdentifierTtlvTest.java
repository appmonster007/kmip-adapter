package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReplacedUniqueIdentifier TTLV Serialization Tests")
class ReplacedUniqueIdentifierTtlvTest
    extends AbstractTtlvSerializationTestSuite<ReplacedUniqueIdentifier> {

  @Override
  public Class<ReplacedUniqueIdentifier> type() {
    return ReplacedUniqueIdentifier.class;
  }

  @Override
  public ReplacedUniqueIdentifier createDefault() {
    return ReplacedUniqueIdentifier
        .builder()
        .value("test-id")
        .build();
  }

  @Override
  public ReplacedUniqueIdentifier createVariant() {
    return ReplacedUniqueIdentifier
        .builder()
        .value("another-id")
        .build();
  }
}