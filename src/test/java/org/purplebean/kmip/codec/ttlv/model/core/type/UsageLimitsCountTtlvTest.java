package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UsageLimitsCount TTLV Serialization Tests")
class UsageLimitsCountTtlvTest extends AbstractTtlvSerializationTestSuite<UsageLimitsCount> {

  @Override
  public Class<UsageLimitsCount> type() {
    return UsageLimitsCount.class;
  }

  @Override
  public UsageLimitsCount createDefault() {
    return UsageLimitsCount
        .builder()
        .value(100L)
        .build();
  }

  @Override
  public UsageLimitsCount createVariant() {
    return UsageLimitsCount
        .builder()
        .value(200L)
        .build();
  }
}