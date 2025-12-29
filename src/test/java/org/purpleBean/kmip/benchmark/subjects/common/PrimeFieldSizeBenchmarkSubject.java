package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PrimeFieldSize;
import java.math.BigInteger;

public class PrimeFieldSizeBenchmarkSubject extends KmipBenchmarkSubject<PrimeFieldSize> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PrimeFieldSizeBenchmarkSubject() throws Exception {
        PrimeFieldSize primeFieldSize = PrimeFieldSize.builder().value(BigInteger.valueOf(2048)).build();
        initialize(primeFieldSize, PrimeFieldSize.class);
    }

    @Override
    public String name() {
        return "PrimeFieldSize";
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