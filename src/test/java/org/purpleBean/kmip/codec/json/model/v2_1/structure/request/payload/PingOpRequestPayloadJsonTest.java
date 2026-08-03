package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.PingOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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