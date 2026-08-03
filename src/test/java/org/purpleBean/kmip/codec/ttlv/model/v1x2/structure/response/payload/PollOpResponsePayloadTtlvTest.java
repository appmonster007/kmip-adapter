package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PollOpResponsePayload Ttlv Serialization Tests")
class PollOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<PollOpResponsePayload> {

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