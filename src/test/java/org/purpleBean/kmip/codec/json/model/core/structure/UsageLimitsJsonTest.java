package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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
