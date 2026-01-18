package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeBenchmarkSubject extends KmipBenchmarkSubject<UnwrapMode> {

    public UnwrapModeBenchmarkSubject() throws Exception {
        UnwrapMode unwrapMode = UnwrapMode.Standard.UNSPECIFIED.inst();
        initialize(unwrapMode, UnwrapMode.class);
    }

    @Override
    public String name() {
        return "UnwrapMode";
    }

}
