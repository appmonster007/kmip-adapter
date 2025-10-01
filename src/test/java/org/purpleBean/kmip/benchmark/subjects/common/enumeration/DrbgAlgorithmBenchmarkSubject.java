package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<DrbgAlgorithm> {

    public DrbgAlgorithmBenchmarkSubject() throws Exception {
        DrbgAlgorithm drbgAlgorithm = new DrbgAlgorithm(DrbgAlgorithm.Standard.UNSPECIFIED);
        initialize(drbgAlgorithm, DrbgAlgorithm.class);
    }

    @Override
    public String name() {
        return "DrbgAlgorithm";
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
