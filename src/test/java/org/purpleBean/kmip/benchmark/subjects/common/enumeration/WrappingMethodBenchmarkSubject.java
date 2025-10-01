package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

public class WrappingMethodBenchmarkSubject extends KmipBenchmarkSubject<WrappingMethod> {

    public WrappingMethodBenchmarkSubject() throws Exception {
        WrappingMethod wrappingMethod = new WrappingMethod(WrappingMethod.Standard.ENCRYPT);
        initialize(wrappingMethod, WrappingMethod.class);
    }

    @Override
    public String name() {
        return "WrappingMethod";
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
