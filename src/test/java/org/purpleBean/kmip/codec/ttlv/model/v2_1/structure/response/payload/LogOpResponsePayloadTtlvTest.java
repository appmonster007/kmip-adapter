package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LogOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogOpResponsePayload Ttlv Serialization Tests")
class LogOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<LogOpResponsePayload> {

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