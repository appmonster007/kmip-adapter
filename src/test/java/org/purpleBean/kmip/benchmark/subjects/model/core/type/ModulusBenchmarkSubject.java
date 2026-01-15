package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusBenchmarkSubject extends KmipBenchmarkSubject<Modulus> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ModulusBenchmarkSubject() throws Exception {
        Modulus modulus = Modulus.builder().value(BigInteger.TEN).build();
        initialize(modulus, Modulus.class);
    }

    @Override
    public String name() {
        return "Modulus";
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