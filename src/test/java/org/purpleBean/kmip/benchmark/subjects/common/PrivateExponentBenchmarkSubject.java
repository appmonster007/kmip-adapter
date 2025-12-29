package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PrivateExponent;
import java.math.BigInteger;

public class PrivateExponentBenchmarkSubject extends KmipBenchmarkSubject<PrivateExponent> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PrivateExponentBenchmarkSubject() throws Exception {
        PrivateExponent privateExponent = PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
        initialize(privateExponent, PrivateExponent.class);
    }

    @Override
    public String name() {
        return "PrivateExponent";
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