package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.UsageLimitsCount;

public class UsageLimitsCountBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsCount> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public UsageLimitsCountBenchmarkSubject() throws Exception {
        UsageLimitsCount usageLimitsCount = UsageLimitsCount.builder().value(100L).build();
        initialize(usageLimitsCount, UsageLimitsCount.class);
    }

    @Override
    public String name() {
        return "UsageLimitsCount";
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