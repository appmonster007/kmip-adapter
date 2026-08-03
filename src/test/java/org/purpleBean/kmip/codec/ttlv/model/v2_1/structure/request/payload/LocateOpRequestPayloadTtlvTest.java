package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LocateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Ttlv Serialization Tests")
class LocateOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<LocateOpRequestPayload> {

  @Override
  public Class<LocateOpRequestPayload> type() {
    return LocateOpRequestPayload.class;
  }

  @Override
  public LocateOpRequestPayload createDefault() {
    return LocateOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public LocateOpRequestPayload createVariant() {
    return LocateOpRequestPayload
        .builder()
        .build();
  }
}