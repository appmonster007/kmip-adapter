package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.GetAttributesOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetAttributesOpRequestPayload Ttlv Serialization Tests")
class GetAttributesOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<GetAttributesOpRequestPayload> {

  @Override
  public Class<GetAttributesOpRequestPayload> type() {
    return GetAttributesOpRequestPayload.class;
  }

  @Override
  public GetAttributesOpRequestPayload createDefault() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public GetAttributesOpRequestPayload createVariant() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }
}