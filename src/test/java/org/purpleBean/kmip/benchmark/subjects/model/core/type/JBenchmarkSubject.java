package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.J;

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

}