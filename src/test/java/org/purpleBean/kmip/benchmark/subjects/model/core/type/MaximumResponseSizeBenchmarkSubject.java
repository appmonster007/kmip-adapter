package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

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

}