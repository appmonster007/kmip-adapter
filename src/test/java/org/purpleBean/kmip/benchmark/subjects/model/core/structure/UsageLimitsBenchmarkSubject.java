package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.structure.UsageLimits;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsBenchmarkSubject extends KmipBenchmarkSubject<UsageLimits> {

    public UsageLimitsBenchmarkSubject() throws Exception {
        UsageLimits usageLimits = UsageLimits.builder()
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

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
