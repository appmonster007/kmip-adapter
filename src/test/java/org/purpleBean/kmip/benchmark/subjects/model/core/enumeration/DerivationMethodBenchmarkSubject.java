package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodBenchmarkSubject extends KmipBenchmarkSubject<DerivationMethod> {

    public DerivationMethodBenchmarkSubject() throws Exception {
        DerivationMethod derivationMethod = DerivationMethod.Standard.PBKDF2.inst();
        initialize(derivationMethod, DerivationMethod.class);
    }

    @Override
    public String name() {
        return "DerivationMethod";
    }

}
