package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("UsageLimitsCount Domain Tests")
class UsageLimitsCountTest extends AbstractKmipDataTypeTestSuite<UsageLimitsCount> {

  @Override
  protected Class<UsageLimitsCount> type() {
    return UsageLimitsCount.class;
  }

  @Override
  protected UsageLimitsCount createDefault() {
    return UsageLimitsCount
        .builder()
        .value(100L)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.LONG_INTEGER;
  }
}