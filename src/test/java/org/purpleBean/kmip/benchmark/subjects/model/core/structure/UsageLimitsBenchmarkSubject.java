package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsBenchmarkSubject extends KmipBenchmarkSubject<UsageLimits> {

  public UsageLimitsBenchmarkSubject() throws Exception {
    UsageLimits usageLimits = UsageLimits
        .builder()
        .usageLimitsTotal(UsageLimitsTotal.of(100L))
        .usageLimitsCount(UsageLimitsCount.of(10L))
        .usageLimitsUnit(UsageLimitsUnit.Standard.BYTE.inst())
        .build();
    initialize(usageLimits, UsageLimits.class);
  }

  @Override
  public String name() {
    return "UsageLimits";
  }

}
