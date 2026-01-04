package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QBenchmarkSubject extends KmipBenchmarkSubject<Q> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public QBenchmarkSubject() throws Exception {
        Q q = Q.builder().value(BigInteger.ONE).build();
        initialize(q, Q.class);
    }

    @Override
    public String name() {
        return "Q";
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