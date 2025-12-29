package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Fresh;

public class FreshBenchmarkSubject extends KmipBenchmarkSubject<Fresh> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public FreshBenchmarkSubject() throws Exception {
        Fresh fresh = Fresh.builder().value(true).build();
        initialize(fresh, Fresh.class);
    }

    @Override
    public String name() {
        return "Fresh";
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