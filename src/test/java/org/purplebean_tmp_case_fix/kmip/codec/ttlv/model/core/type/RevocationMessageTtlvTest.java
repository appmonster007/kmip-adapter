package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.RevocationMessage;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RevocationMessage TTLV Serialization Tests")
class RevocationMessageTtlvTest extends AbstractTtlvSerializationTestSuite<RevocationMessage> {

  @Override
  public Class<RevocationMessage> type() {
    return RevocationMessage.class;
  }

  @Override
  public RevocationMessage createDefault() {
    return RevocationMessage
        .builder()
        .value("test-revocation-message")
        .build();
  }

  @Override
  public RevocationMessage createVariant() {
    return RevocationMessage
        .builder()
        .value("another-revocation-message")
        .build();
  }
}