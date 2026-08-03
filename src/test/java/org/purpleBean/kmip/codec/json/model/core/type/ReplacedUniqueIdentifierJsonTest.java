package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplacedUniqueIdentifier JSON Serialization Tests")
class ReplacedUniqueIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<ReplacedUniqueIdentifier> {

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