package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("RevocationMessage Domain Tests")
class RevocationMessageTest extends AbstractKmipDataTypeTestSuite<RevocationMessage> {

  @Override
  protected Class<RevocationMessage> type() {
    return RevocationMessage.class;
  }

  @Override
  protected RevocationMessage createDefault() {
    return RevocationMessage
        .builder()
        .value("test-revocation-message")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}