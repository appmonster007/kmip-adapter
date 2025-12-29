package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.D;
import java.math.BigInteger;

public class DBenchmarkSubject extends KmipBenchmarkSubject<D> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public DBenchmarkSubject() throws Exception {
        D d = D.builder().value(BigInteger.ONE).build();
        initialize(d, D.class);
    }

    @Override
    public String name() {
        return "D";
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