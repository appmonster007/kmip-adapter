package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.UsageLimitsTotal;

public class UsageLimitsTotalBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsTotal> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public UsageLimitsTotalBenchmarkSubject() throws Exception {
        UsageLimitsTotal usageLimitsTotal = UsageLimitsTotal.builder().value(1000L).build();
        initialize(usageLimitsTotal, UsageLimitsTotal.class);
    }

    @Override
    public String name() {
        return "UsageLimitsTotal";
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