package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionBenchmarkSubject extends KmipBenchmarkSubject<PutFunction> {

    public PutFunctionBenchmarkSubject() throws Exception {
        PutFunction putFunction = PutFunction.Standard.NEW.inst();
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
