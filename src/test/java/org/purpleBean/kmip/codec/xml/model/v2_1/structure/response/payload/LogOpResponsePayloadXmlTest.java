package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LogOpResponsePayload Xml Serialization Tests")
class LogOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<LogOpResponsePayload> {

  @Override
  public Class<LogOpResponsePayload> type() {
    return LogOpResponsePayload.class;
  }

  @Override
  public LogOpResponsePayload createDefault() {
    return LogOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public LogOpResponsePayload createVariant() {
    return LogOpResponsePayload
        .builder()
        .build();
  }
}