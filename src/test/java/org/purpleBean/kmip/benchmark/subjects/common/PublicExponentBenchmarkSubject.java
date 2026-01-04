package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentBenchmarkSubject extends KmipBenchmarkSubject<PublicExponent> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PublicExponentBenchmarkSubject() throws Exception {
        PublicExponent publicExponent = PublicExponent.builder().value(BigInteger.valueOf(65537)).build();
        initialize(publicExponent, PublicExponent.class);
    }

    @Override
    public String name() {
        return "PublicExponent";
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