package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPBenchmarkSubject extends KmipBenchmarkSubject<PrimeExponentP> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PrimeExponentPBenchmarkSubject() throws Exception {
        PrimeExponentP primeExponentP = PrimeExponentP.builder().value(BigInteger.valueOf(65537)).build();
        initialize(primeExponentP, PrimeExponentP.class);
    }

    @Override
    public String name() {
        return "PrimeExponentP";
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