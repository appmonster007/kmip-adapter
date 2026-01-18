package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeBenchmarkSubject extends KmipBenchmarkSubject<PrimeFieldSize> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public PrimeFieldSizeBenchmarkSubject() throws Exception {
        PrimeFieldSize primeFieldSize = PrimeFieldSize.builder().value(BigInteger.valueOf(2048)).build();
        initialize(primeFieldSize, PrimeFieldSize.class);
    }

    @Override
    public String name() {
        return "PrimeFieldSize";
    }

}