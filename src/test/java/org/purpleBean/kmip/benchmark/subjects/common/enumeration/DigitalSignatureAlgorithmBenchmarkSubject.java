package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<DigitalSignatureAlgorithm> {

    public DigitalSignatureAlgorithmBenchmarkSubject() throws Exception {
        DigitalSignatureAlgorithm digitalSignatureAlgorithm = new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION);
        initialize(digitalSignatureAlgorithm, DigitalSignatureAlgorithm.class);
    }

    @Override
    public String name() {
        return "DigitalSignatureAlgorithm";
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
