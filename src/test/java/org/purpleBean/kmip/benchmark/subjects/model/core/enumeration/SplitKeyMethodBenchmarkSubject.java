package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyMethod> {

    public SplitKeyMethodBenchmarkSubject() throws Exception {
        SplitKeyMethod splitKeyMethod = SplitKeyMethod.Standard.XOR.inst();
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
