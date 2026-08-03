package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LogOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.LogMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogOpRequestPayload Ttlv Serialization Tests")
class LogOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<LogOpRequestPayload> {

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