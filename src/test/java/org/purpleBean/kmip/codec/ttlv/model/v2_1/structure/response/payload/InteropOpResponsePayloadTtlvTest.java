package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.InteropOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InteropOpResponsePayload Ttlv Serialization Tests")
class InteropOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<InteropOpResponsePayload> {

  @Override
  public Class<InteropOpResponsePayload> type() {
    return InteropOpResponsePayload.class;
  }

  @Override
  public InteropOpResponsePayload createDefault() {
    return InteropOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public InteropOpResponsePayload createVariant() {
    return InteropOpResponsePayload
        .builder()
        .build();
  }
}