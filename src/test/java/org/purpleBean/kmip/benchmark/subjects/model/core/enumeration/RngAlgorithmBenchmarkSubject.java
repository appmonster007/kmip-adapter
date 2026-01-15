package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<RngAlgorithm> {

    public RngAlgorithmBenchmarkSubject() throws Exception {
        RngAlgorithm rngAlgorithm = RngAlgorithm.Standard.UNSPECIFIED.inst();
        initialize(rngAlgorithm, RngAlgorithm.class);
    }

    @Override
    public String name() {
        return "RngAlgorithm";
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
