package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

public class UsageLimitsTotalBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsTotal> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public UsageLimitsTotalBenchmarkSubject() throws Exception {
        UsageLimitsTotal usageLimitsTotal = UsageLimitsTotal.builder().value(1000L).build();
        initialize(usageLimitsTotal, UsageLimitsTotal.class);
    }

    @Override
    public String name() {
        return "UsageLimitsTotal";
    }

}