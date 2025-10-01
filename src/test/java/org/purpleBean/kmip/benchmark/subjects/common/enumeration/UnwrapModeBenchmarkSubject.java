package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeBenchmarkSubject extends KmipBenchmarkSubject<UnwrapMode> {

    public UnwrapModeBenchmarkSubject() throws Exception {
        UnwrapMode unwrapMode = new UnwrapMode(UnwrapMode.Standard.UNSPECIFIED);
        initialize(unwrapMode, UnwrapMode.class);
    }

    @Override
    public String name() {
        return "UnwrapMode";
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
