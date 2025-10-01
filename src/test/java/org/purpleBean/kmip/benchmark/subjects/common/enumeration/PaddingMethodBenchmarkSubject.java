package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodBenchmarkSubject extends KmipBenchmarkSubject<PaddingMethod> {

    public PaddingMethodBenchmarkSubject() throws Exception {
        PaddingMethod paddingMethod = new PaddingMethod(PaddingMethod.Standard.NONE);
        initialize(paddingMethod, PaddingMethod.class);
    }

    @Override
    public String name() {
        return "PaddingMethod";
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
