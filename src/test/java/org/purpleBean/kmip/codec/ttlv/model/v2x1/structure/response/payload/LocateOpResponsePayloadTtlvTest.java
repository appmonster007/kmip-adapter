package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LocateOpResponsePayload Ttlv Serialization Tests")
class LocateOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<LocateOpResponsePayload> {

  @Override
  public Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  public LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public LocateOpResponsePayload createVariant() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }
}
