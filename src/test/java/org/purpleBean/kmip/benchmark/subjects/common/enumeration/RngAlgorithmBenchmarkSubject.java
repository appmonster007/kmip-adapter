package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<RngAlgorithm> {

    public RngAlgorithmBenchmarkSubject() throws Exception {
        RngAlgorithm rngAlgorithm = new RngAlgorithm(RngAlgorithm.Standard.UNSPECIFIED);
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
