package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CheckOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CheckOpRequestPayload Ttlv Serialization Tests")
class CheckOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CheckOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CheckOpRequestPayload> type() {
    return CheckOpRequestPayload.class;
  }

  @Override
  public CheckOpRequestPayload createDefault() {
    return CheckOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .cryptographicUsageMask(CryptographicUsageMask.of(1))
        .leaseTime(LeaseTime.of(3600))
        .build();
  }

  @Override
  public CheckOpRequestPayload createVariant() {
    return CheckOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .usageLimitsCount(UsageLimitsCount.of(200L))
        .cryptographicUsageMask(CryptographicUsageMask.of(2))
        .leaseTime(LeaseTime.of(7200))
        .build();
  }
}
