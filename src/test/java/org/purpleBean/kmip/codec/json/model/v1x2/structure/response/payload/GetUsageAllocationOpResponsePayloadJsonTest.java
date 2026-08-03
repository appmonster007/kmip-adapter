package org.purpleBean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.GetUsageAllocationOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetUsageAllocationOpResponsePayload Json Serialization Tests")
class GetUsageAllocationOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<GetUsageAllocationOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<GetUsageAllocationOpResponsePayload> type() {
    return GetUsageAllocationOpResponsePayload.class;
  }

  @Override
  public GetUsageAllocationOpResponsePayload createDefault() {
    return GetUsageAllocationOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public GetUsageAllocationOpResponsePayload createVariant() {
    return GetUsageAllocationOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
