package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetUsageAllocationOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetUsageAllocationOpRequestPayload Ttlv Serialization Tests")
class GetUsageAllocationOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<GetUsageAllocationOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<GetUsageAllocationOpRequestPayload> type() {
    return GetUsageAllocationOpRequestPayload.class;
  }

  @Override
  public GetUsageAllocationOpRequestPayload createDefault() {
    return GetUsageAllocationOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .build();
  }

  @Override
  public GetUsageAllocationOpRequestPayload createVariant() {
    return GetUsageAllocationOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .usageLimitsCount(UsageLimitsCount.of(200L))
        .build();
  }
}
