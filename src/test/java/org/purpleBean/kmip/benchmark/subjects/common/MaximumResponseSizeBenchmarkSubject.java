package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeBenchmarkSubject extends KmipBenchmarkSubject<MaximumResponseSize> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public MaximumResponseSizeBenchmarkSubject() throws Exception {
        MaximumResponseSize maximumResponseSize = MaximumResponseSize.builder().value(1024).build();
        initialize(maximumResponseSize, MaximumResponseSize.class);
    }

    @Override
    public String name() {
        return "MaximumResponseSize";
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