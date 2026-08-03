package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UsageLimitsTotal JSON Serialization Tests")
class UsageLimitsTotalJsonTest extends AbstractJsonSerializationTestSuite<UsageLimitsTotal> {

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