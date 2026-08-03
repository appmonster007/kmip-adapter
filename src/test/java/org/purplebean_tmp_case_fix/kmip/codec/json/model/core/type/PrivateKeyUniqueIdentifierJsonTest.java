package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PrivateKeyUniqueIdentifier JSON Serialization Tests")
class PrivateKeyUniqueIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<PrivateKeyUniqueIdentifier> {

  @Override
  public Class<PrivateKeyUniqueIdentifier> type() {
    return PrivateKeyUniqueIdentifier.class;
  }

  @Override
  public PrivateKeyUniqueIdentifier createDefault() {
    return PrivateKeyUniqueIdentifier
        .builder()
        .value("test-private-key-id")
        .build();
  }

  @Override
  public PrivateKeyUniqueIdentifier createVariant() {
    return PrivateKeyUniqueIdentifier
        .builder()
        .value("another-private-key-id")
        .build();
  }
}