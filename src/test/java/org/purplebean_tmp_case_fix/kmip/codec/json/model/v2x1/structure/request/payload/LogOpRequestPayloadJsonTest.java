package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.LogMessage;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogOpRequestPayload Json Serialization Tests")
class LogOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<LogOpRequestPayload> {

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