package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<CryptographicAlgorithm> {

    public CryptographicAlgorithmBenchmarkSubject() throws Exception {
        CryptographicAlgorithm cryptographicAlgorithm = new CryptographicAlgorithm(CryptographicAlgorithm.Standard.DES);
        initialize(cryptographicAlgorithm, CryptographicAlgorithm.class);
    }

    @Override
    public String name() {
        return "CryptographicAlgorithm";
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
