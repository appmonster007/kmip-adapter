package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JBenchmarkSubject extends KmipBenchmarkSubject<J> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public JBenchmarkSubject() throws Exception {
        J j = J.builder().value(BigInteger.ONE).build();
        initialize(j, J.class);
    }

    @Override
    public String name() {
        return "J";
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