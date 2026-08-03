package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

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
