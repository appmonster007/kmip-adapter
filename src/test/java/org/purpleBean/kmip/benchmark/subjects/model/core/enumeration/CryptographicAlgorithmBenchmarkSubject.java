package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<CryptographicAlgorithm> {

    public CryptographicAlgorithmBenchmarkSubject() throws Exception {
        CryptographicAlgorithm cryptographicAlgorithm = CryptographicAlgorithm.Standard.DES.inst();
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
