package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InteropOpResponsePayload Json Serialization Tests")
class InteropOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<InteropOpResponsePayload> {

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