package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

public class SplitKeyMethodBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyMethod> {

    public SplitKeyMethodBenchmarkSubject() throws Exception {
        SplitKeyMethod splitKeyMethod = new SplitKeyMethod(SplitKeyMethod.Standard.XOR);
        initialize(splitKeyMethod, SplitKeyMethod.class);
    }

    @Override
    public String name() {
        return "SplitKeyMethod";
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
