package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.PollOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PollOpResponsePayload Json Serialization Tests")
class PollOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<PollOpResponsePayload> {

  @Override
  public Class<PollOpResponsePayload> type() {
    return PollOpResponsePayload.class;
  }

  @Override
  public PollOpResponsePayload createDefault() {
    return PollOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public PollOpResponsePayload createVariant() {
    return PollOpResponsePayload
        .builder()
        .build();
  }
}