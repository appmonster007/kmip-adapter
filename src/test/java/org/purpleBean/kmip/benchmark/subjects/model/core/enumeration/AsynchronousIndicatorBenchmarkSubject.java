package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorBenchmarkSubject extends KmipBenchmarkSubject<AsynchronousIndicator> {

    public AsynchronousIndicatorBenchmarkSubject() throws Exception {
        AsynchronousIndicator asynchronousIndicator = AsynchronousIndicator.Standard.MANDATORY.inst();
        initialize(asynchronousIndicator, AsynchronousIndicator.class);
    }

    @Override
    public String name() {
        return "AsynchronousIndicator";
    }

}
