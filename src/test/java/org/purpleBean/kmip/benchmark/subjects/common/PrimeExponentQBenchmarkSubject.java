package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQBenchmarkSubject extends KmipBenchmarkSubject<PrimeExponentQ> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public PrimeExponentQBenchmarkSubject() throws Exception {
        PrimeExponentQ primeExponentQ = PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
        initialize(primeExponentQ, PrimeExponentQ.class);
    }

    @Override
    public String name() {
        return "PrimeExponentQ";
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