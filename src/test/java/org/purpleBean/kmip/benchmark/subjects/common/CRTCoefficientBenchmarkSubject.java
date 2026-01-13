package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientBenchmarkSubject extends KmipBenchmarkSubject<CRTCoefficient> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CRTCoefficientBenchmarkSubject() throws Exception {
        CRTCoefficient cRTCoefficient = CRTCoefficient.builder().value(BigInteger.valueOf(12345)).build();
        initialize(cRTCoefficient, CRTCoefficient.class);
    }

    @Override
    public String name() {
        return "CRTCoefficient";
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