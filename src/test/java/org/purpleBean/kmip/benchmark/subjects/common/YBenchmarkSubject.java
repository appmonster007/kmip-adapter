package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Y;
import java.math.BigInteger;

public class YBenchmarkSubject extends KmipBenchmarkSubject<Y> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public YBenchmarkSubject() throws Exception {
        Y y = Y.builder().value(BigInteger.ONE).build();
        initialize(y, Y.class);
    }

    @Override
    public String name() {
        return "Y";
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