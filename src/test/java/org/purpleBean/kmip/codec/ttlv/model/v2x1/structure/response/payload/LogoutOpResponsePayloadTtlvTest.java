package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.LogoutOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogoutOpResponsePayload Ttlv Serialization Tests")
class LogoutOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<LogoutOpResponsePayload> {

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