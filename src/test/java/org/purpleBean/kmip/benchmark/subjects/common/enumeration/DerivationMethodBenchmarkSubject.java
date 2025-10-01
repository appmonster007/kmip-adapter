package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodBenchmarkSubject extends KmipBenchmarkSubject<DerivationMethod> {

    public DerivationMethodBenchmarkSubject() throws Exception {
        DerivationMethod derivationMethod = new DerivationMethod(DerivationMethod.Standard.PBKDF2);
        initialize(derivationMethod, DerivationMethod.class);
    }

    @Override
    public String name() {
        return "DerivationMethod";
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
