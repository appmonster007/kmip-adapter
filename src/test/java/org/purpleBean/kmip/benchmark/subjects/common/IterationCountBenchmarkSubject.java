package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountBenchmarkSubject extends KmipBenchmarkSubject<IterationCount> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public IterationCountBenchmarkSubject() throws Exception {
        IterationCount iterationCount = IterationCount.builder().value(1000).build();
        initialize(iterationCount, IterationCount.class);
    }

    @Override
    public String name() {
        return "IterationCount";
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