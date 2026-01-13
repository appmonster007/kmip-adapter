package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Issuer;

public class IssuerBenchmarkSubject extends KmipBenchmarkSubject<Issuer> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public IssuerBenchmarkSubject() throws Exception {
        Issuer issuer = Issuer.builder().value("test-issuer").build();
        initialize(issuer, Issuer.class);
    }

    @Override
    public String name() {
        return "Issuer";
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