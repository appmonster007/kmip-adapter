package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionBenchmarkSubject extends KmipBenchmarkSubject<PutFunction> {

    public PutFunctionBenchmarkSubject() throws Exception {
        PutFunction putFunction = new PutFunction(PutFunction.Standard.NEW);
        initialize(putFunction, PutFunction.class);
    }

    @Override
    public String name() {
        return "PutFunction";
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
