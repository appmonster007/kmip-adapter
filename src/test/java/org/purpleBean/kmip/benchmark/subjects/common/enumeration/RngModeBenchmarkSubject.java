package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeBenchmarkSubject extends KmipBenchmarkSubject<RngMode> {

    public RngModeBenchmarkSubject() throws Exception {
        RngMode rngMode = new RngMode(RngMode.Standard.UNSPECIFIED);
        initialize(rngMode, RngMode.class);
    }

    @Override
    public String name() {
        return "RngMode";
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
