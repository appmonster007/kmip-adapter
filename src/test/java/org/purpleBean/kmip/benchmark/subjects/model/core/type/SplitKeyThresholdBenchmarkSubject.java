package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyThresholdBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyThreshold> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SplitKeyThresholdBenchmarkSubject() throws Exception {
        SplitKeyThreshold splitKeyThreshold = SplitKeyThreshold.builder().value(2).build();
        initialize(splitKeyThreshold, SplitKeyThreshold.class);
    }

    @Override
    public String name() {
        return "SplitKeyThreshold";
    }

}