package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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