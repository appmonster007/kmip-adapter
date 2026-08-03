package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LogoutOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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