package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.SplitKeyParts;

public class SplitKeyPartsBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyParts> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SplitKeyPartsBenchmarkSubject() throws Exception {
        SplitKeyParts splitKeyParts = SplitKeyParts.builder().value(2).build();
        initialize(splitKeyParts, SplitKeyParts.class);
    }

    @Override
    public String name() {
        return "SplitKeyParts";
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