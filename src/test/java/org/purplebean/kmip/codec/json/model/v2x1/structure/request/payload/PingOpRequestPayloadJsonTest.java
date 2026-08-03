package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PingOpRequestPayload Json Serialization Tests")
class PingOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<PingOpRequestPayload> {

  @Override
  public Class<PingOpRequestPayload> type() {
    return PingOpRequestPayload.class;
  }

  @Override
  public PingOpRequestPayload createDefault() {
    return PingOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public PingOpRequestPayload createVariant() {
    return PingOpRequestPayload
        .builder()
        .build();
  }
}