package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LogOpRequestPayload Xml Serialization Tests")
class LogOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<LogOpRequestPayload> {

  @Override
  public Class<LogOpRequestPayload> type() {
    return LogOpRequestPayload.class;
  }

  @Override
  public LogOpRequestPayload createDefault() {
    return LogOpRequestPayload
        .builder()
        .logMessage(LogMessage
            .builder()
            .value("test-log-message")
            .build())
        .build();
  }

  @Override
  public LogOpRequestPayload createVariant() {
    return LogOpRequestPayload
        .builder()
        .logMessage(LogMessage
            .builder()
            .value("test-log-message-variant")
            .build())
        .build();
  }
}