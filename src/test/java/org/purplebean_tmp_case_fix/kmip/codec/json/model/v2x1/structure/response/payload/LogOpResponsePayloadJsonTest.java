package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogOpResponsePayload Json Serialization Tests")
class LogOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LogOpResponsePayload> {

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