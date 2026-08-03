package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("UsageLimitsTotal Domain Tests")
class UsageLimitsTotalTest extends AbstractKmipDataTypeTestSuite<UsageLimitsTotal> {

  @Override
  protected Class<UsageLimitsTotal> type() {
    return UsageLimitsTotal.class;
  }

  @Override
  protected UsageLimitsTotal createDefault() {
    return UsageLimitsTotal
        .builder()
        .value(1000L)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.LONG_INTEGER;
  }
}