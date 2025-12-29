package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AsynchronousIndicator;

public class AsynchronousIndicatorBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousIndicator> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AsynchronousIndicatorBenchmarkSubject() throws Exception {
        AsynchronousIndicator asynchronousIndicator = AsynchronousIndicator.builder().value(true).build();
        initialize(asynchronousIndicator, AsynchronousIndicator.class);
    }

    @Override
    public String name() {
        return "AsynchronousIndicator";
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