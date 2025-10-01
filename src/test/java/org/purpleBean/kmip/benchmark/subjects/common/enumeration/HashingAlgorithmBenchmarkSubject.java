package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

public class HashingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<HashingAlgorithm> {

    public HashingAlgorithmBenchmarkSubject() throws Exception {
        HashingAlgorithm hashingAlgorithm = new HashingAlgorithm(HashingAlgorithm.Standard.MD2);
        initialize(hashingAlgorithm, HashingAlgorithm.class);
    }

    @Override
    public String name() {
        return "HashingAlgorithm";
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
