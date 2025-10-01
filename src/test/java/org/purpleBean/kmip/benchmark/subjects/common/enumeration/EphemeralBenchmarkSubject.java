package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralBenchmarkSubject extends KmipBenchmarkSubject<Ephemeral> {

    public EphemeralBenchmarkSubject() throws Exception {
        Ephemeral ephemeral = new Ephemeral(Ephemeral.Standard.DATA);
        initialize(ephemeral, Ephemeral.class);
    }

    @Override
    public String name() {
        return "Ephemeral";
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
