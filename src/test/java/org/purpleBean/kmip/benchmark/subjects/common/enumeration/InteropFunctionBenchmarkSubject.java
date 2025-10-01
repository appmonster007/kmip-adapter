package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionBenchmarkSubject extends KmipBenchmarkSubject<InteropFunction> {

    public InteropFunctionBenchmarkSubject() throws Exception {
        InteropFunction interopFunction = new InteropFunction(InteropFunction.Standard.BEGIN);
        initialize(interopFunction, InteropFunction.class);
    }

    @Override
    public String name() {
        return "InteropFunction";
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
