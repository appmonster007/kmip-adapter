package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<ShreddingAlgorithm> {

    public ShreddingAlgorithmBenchmarkSubject() throws Exception {
        ShreddingAlgorithm shreddingAlgorithm = new ShreddingAlgorithm(ShreddingAlgorithm.Standard.UNSPECIFIED);
        initialize(shreddingAlgorithm, ShreddingAlgorithm.class);
    }

    @Override
    public String name() {
        return "ShreddingAlgorithm";
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
