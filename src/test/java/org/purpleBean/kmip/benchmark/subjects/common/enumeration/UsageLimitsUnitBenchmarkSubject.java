package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

public class UsageLimitsUnitBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsUnit> {

    public UsageLimitsUnitBenchmarkSubject() throws Exception {
        UsageLimitsUnit usageLimitsUnit = new UsageLimitsUnit(UsageLimitsUnit.Standard.BYTE);
        initialize(usageLimitsUnit, UsageLimitsUnit.class);
    }

    @Override
    public String name() {
        return "UsageLimitsUnit";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
