package org.purpleBean.kmip.benchmark.subjects.model.v3_0.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.enumeration.Ephemeral;

public class EphemeralBenchmarkSubject extends KmipBenchmarkSubject<Ephemeral> {

    public EphemeralBenchmarkSubject() throws Exception {
        Ephemeral ephemeral = Ephemeral.Standard.DATA.inst();
        initialize(ephemeral, Ephemeral.class);
    }

    @Override
    public String name() {
        return "Ephemeral";
    }

}
