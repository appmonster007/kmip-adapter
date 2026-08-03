package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.RevocationMessage;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RevocationMessage XML Serialization Tests")
class RevocationMessageXmlTest extends AbstractXmlSerializationTestSuite<RevocationMessage> {

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