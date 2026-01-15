package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<DigitalSignatureAlgorithm> {

    public DigitalSignatureAlgorithmBenchmarkSubject() throws Exception {
        DigitalSignatureAlgorithm digitalSignatureAlgorithm = DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
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
