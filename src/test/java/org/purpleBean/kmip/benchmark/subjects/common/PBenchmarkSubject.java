package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.P;
import java.math.BigInteger;

public class PBenchmarkSubject extends KmipBenchmarkSubject<P> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PBenchmarkSubject() throws Exception {
        P p = P.builder().value(BigInteger.ONE).build();
        initialize(p, P.class);
    }

    @Override
    public String name() {
        return "P";
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