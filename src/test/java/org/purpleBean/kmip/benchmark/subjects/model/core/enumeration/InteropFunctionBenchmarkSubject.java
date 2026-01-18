package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;

public class InteropFunctionBenchmarkSubject extends KmipBenchmarkSubject<InteropFunction> {

    public InteropFunctionBenchmarkSubject() throws Exception {
        InteropFunction interopFunction = InteropFunction.Standard.BEGIN.inst();
        initialize(interopFunction, InteropFunction.class);
    }

    @Override
    public String name() {
        return "InteropFunction";
    }

}
