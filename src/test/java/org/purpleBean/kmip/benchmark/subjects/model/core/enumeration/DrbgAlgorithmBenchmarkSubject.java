package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<DrbgAlgorithm> {

    public DrbgAlgorithmBenchmarkSubject() throws Exception {
        DrbgAlgorithm drbgAlgorithm = DrbgAlgorithm.Standard.UNSPECIFIED.inst();
        initialize(drbgAlgorithm, DrbgAlgorithm.class);
    }

    @Override
    public String name() {
        return "DrbgAlgorithm";
    }

}
