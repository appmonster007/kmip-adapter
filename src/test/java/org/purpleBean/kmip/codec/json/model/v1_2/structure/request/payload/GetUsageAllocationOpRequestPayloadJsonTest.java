package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetUsageAllocationOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetUsageAllocationOpRequestPayload Json Serialization Tests")
class GetUsageAllocationOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<GetUsageAllocationOpRequestPayload> {

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
        return GetUsageAllocationOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .usageLimitsCount(UsageLimitsCount.of(100L))
                .build();
    }

    @Override
    public GetUsageAllocationOpRequestPayload createVariant() {
        return GetUsageAllocationOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .usageLimitsCount(UsageLimitsCount.of(200L))
                .build();
    }
}
