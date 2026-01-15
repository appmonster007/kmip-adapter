package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

public class AsynchronousIndicatorBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousIndicator> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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