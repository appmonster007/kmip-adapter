package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("NetworkIdentifier Domain Tests")
class NetworkIdentifierTest extends AbstractKmipDataTypeTestSuite<NetworkIdentifier> {

  @Override
  protected Class<NetworkIdentifier> type() {
    return NetworkIdentifier.class;
  }

  @Override
  protected NetworkIdentifier createDefault() {
    return NetworkIdentifier
        .builder()
        .value("test-network-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}