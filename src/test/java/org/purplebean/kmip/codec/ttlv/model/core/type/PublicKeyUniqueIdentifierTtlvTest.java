package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PublicKeyUniqueIdentifier TTLV Serialization Tests")
class PublicKeyUniqueIdentifierTtlvTest
    extends AbstractTtlvSerializationTestSuite<PublicKeyUniqueIdentifier> {

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