package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YBenchmarkSubject extends KmipBenchmarkSubject<Y> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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