package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UsageLimitsTotal TTLV Serialization Tests")
class UsageLimitsTotalTtlvTest extends AbstractTtlvSerializationTestSuite<UsageLimitsTotal> {

  @Override
  public Class<UsageLimitsTotal> type() {
    return UsageLimitsTotal.class;
  }

  @Override
  public UsageLimitsTotal createDefault() {
    return UsageLimitsTotal
        .builder()
        .value(1000L)
        .build();
  }

  @Override
  public UsageLimitsTotal createVariant() {
    return UsageLimitsTotal
        .builder()
        .value(2000L)
        .build();
  }
}