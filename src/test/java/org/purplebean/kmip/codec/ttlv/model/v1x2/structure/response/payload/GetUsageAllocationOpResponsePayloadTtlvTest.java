package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetUsageAllocationOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetUsageAllocationOpResponsePayload Ttlv Serialization Tests")
class GetUsageAllocationOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<GetUsageAllocationOpResponsePayload> {

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
