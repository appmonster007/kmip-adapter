package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousIndicator> {

    public AsynchronousIndicatorBenchmarkSubject() throws Exception {
        AsynchronousIndicator asynchronousIndicator = new AsynchronousIndicator(AsynchronousIndicator.Standard.MANDATORY);
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
