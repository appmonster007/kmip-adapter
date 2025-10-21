package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.LeaseTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class LeaseTimeBenchmarkSubject extends KmipBenchmarkSubject<LeaseTime> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public LeaseTimeBenchmarkSubject() throws Exception {
        var fixed = 100;
        LeaseTime leaseTime = LeaseTime.builder().value(fixed).build();
        initialize(leaseTime, LeaseTime.class);
    }

    @Override
    public String name() {
        return "LeaseTime";
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
