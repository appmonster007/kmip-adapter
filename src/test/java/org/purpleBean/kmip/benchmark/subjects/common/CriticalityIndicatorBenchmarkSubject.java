package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorBenchmarkSubject extends KmipBenchmarkSubject<CriticalityIndicator> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CriticalityIndicatorBenchmarkSubject() throws Exception {
        CriticalityIndicator criticalityIndicator = CriticalityIndicator.builder().value(true).build();
        initialize(criticalityIndicator, CriticalityIndicator.class);
    }

    @Override
    public String name() {
        return "CriticalityIndicator";
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