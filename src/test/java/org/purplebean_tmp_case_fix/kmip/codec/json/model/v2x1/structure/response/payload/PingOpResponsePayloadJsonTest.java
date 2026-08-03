package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.PingOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PingOpResponsePayload Json Serialization Tests")
class PingOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<PingOpResponsePayload> {

  @Override
  public Class<PingOpResponsePayload> type() {
    return PingOpResponsePayload.class;
  }

  @Override
  public PingOpResponsePayload createDefault() {
    return PingOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public PingOpResponsePayload createVariant() {
    return PingOpResponsePayload
        .builder()
        .build();
  }
}