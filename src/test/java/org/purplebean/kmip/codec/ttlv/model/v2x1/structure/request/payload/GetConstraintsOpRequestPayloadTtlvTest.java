package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetConstraintsOpRequestPayload Ttlv Serialization Tests")
class GetConstraintsOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<GetConstraintsOpRequestPayload> {

  @Override
  public Class<GetConstraintsOpRequestPayload> type() {
    return GetConstraintsOpRequestPayload.class;
  }

  @Override
  public GetConstraintsOpRequestPayload createDefault() {
    return GetConstraintsOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public GetConstraintsOpRequestPayload createVariant() {
    return GetConstraintsOpRequestPayload
        .builder()
        .build();
  }
}