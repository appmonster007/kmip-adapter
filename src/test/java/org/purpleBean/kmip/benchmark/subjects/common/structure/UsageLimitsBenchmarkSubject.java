package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

public class UsageLimitsBenchmarkSubject extends KmipBenchmarkSubject<UsageLimits> {

    public UsageLimitsBenchmarkSubject() throws Exception {
        UsageLimits usageLimits = UsageLimits.builder()
                .usageLimitsTotal(UsageLimitsTotal.of(100L))
                .usageLimitsCount(UsageLimitsCount.of(10L))
                .usageLimitsUnit(new UsageLimitsUnit(UsageLimitsUnit.Standard.BYTE))
                .build();
        initialize(usageLimits, UsageLimits.class);
    }

    @Override
    public String name() {
        return "UsageLimits";
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
