package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsUnit> {

  public UsageLimitsUnitBenchmarkSubject() throws Exception {
    UsageLimitsUnit usageLimitsUnit = UsageLimitsUnit.Standard.BYTE.inst();
    initialize(usageLimitsUnit, UsageLimitsUnit.class);
  }

  @Override
  public String name() {
    return "UsageLimitsUnit";
  }

}
