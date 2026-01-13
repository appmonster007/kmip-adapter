package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.IssuerAlternativeName;

public class IssuerAlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<IssuerAlternativeName> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public IssuerAlternativeNameBenchmarkSubject() throws Exception {
        IssuerAlternativeName issuerAlternativeName = IssuerAlternativeName.of("test-issuer-alt-name".getBytes());
        initialize(issuerAlternativeName, IssuerAlternativeName.class);
    }

    @Override
    public String name() {
        return "IssuerAlternativeName";
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