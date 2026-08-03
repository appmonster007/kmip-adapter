package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NetworkIdentifier TTLV Serialization Tests")
class NetworkIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<NetworkIdentifier> {

  @Override
  public Class<NetworkIdentifier> type() {
    return NetworkIdentifier.class;
  }

  @Override
  public NetworkIdentifier createDefault() {
    return NetworkIdentifier
        .builder()
        .value("test-network-id")
        .build();
  }

  @Override
  public NetworkIdentifier createVariant() {
    return NetworkIdentifier
        .builder()
        .value("another-network-id")
        .build();
  }
}