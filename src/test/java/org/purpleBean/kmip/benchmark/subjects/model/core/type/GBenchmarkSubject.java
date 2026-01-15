package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.G;

import java.math.BigInteger;

public class GBenchmarkSubject extends KmipBenchmarkSubject<G> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public GBenchmarkSubject() throws Exception {
        G g = G.builder().value(BigInteger.ONE).build();
        initialize(g, G.class);
    }

    @Override
    public String name() {
        return "G";
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