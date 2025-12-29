package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyThreshold> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public SplitKeyThresholdBenchmarkSubject() throws Exception {
        SplitKeyThreshold splitKeyThreshold = SplitKeyThreshold.builder().value(2).build();
        initialize(splitKeyThreshold, SplitKeyThreshold.class);
    }

    @Override
    public String name() {
        return "SplitKeyThreshold";
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