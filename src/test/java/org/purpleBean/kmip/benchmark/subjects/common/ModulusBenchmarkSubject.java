package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusBenchmarkSubject extends KmipBenchmarkSubject<Modulus> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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