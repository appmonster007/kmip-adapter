package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogoutOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LogoutOpResponsePayload Xml Serialization Tests")
class LogoutOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<LogoutOpResponsePayload> {

  @Override
  public Class<LogoutOpResponsePayload> type() {
    return LogoutOpResponsePayload.class;
  }

  @Override
  public LogoutOpResponsePayload createDefault() {
    return LogoutOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public LogoutOpResponsePayload createVariant() {
    return LogoutOpResponsePayload
        .builder()
        .build();
  }
}