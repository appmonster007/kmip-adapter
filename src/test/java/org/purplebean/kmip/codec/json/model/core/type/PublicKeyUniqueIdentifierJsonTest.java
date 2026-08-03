package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyUniqueIdentifier JSON Serialization Tests")
class PublicKeyUniqueIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<PublicKeyUniqueIdentifier> {

  @Override
  public Class<PublicKeyUniqueIdentifier> type() {
    return PublicKeyUniqueIdentifier.class;
  }

  @Override
  public PublicKeyUniqueIdentifier createDefault() {
    return PublicKeyUniqueIdentifier
        .builder()
        .value("test-key-id")
        .build();
  }

  @Override
  public PublicKeyUniqueIdentifier createVariant() {
    return PublicKeyUniqueIdentifier
        .builder()
        .value("another-key-id")
        .build();
  }
}