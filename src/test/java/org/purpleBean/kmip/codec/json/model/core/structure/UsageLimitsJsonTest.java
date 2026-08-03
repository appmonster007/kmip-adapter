package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.structure.UsageLimits;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UsageLimits Json Serialization Tests")
class UsageLimitsJsonTest extends AbstractJsonSerializationTestSuite<UsageLimits> {

  @Override
  public Class<UsageLimits> type() {
    return UsageLimits.class;
  }

  @Override
  public UsageLimits createDefault() {
    return UsageLimits
        .builder()
        .usageLimitsTotal(UsageLimitsTotal.of(100L))
        .usageLimitsCount(UsageLimitsCount.of(10L))
        .usageLimitsUnit(UsageLimitsUnit.Standard.BYTE.inst())
        .build();
  }
}
