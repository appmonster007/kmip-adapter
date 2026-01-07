package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;

public class CryptographicDomainParametersBenchmarkSubject extends KmipBenchmarkSubject<CryptographicDomainParameters> {

    public CryptographicDomainParametersBenchmarkSubject() throws Exception {
        CryptographicDomainParameters cryptographicDomainParameters = CryptographicDomainParameters.builder()
                .qlength(Qlength.of(256))
                .recommendedCurve(new RecommendedCurve(RecommendedCurve.Standard.P_256))
                .build();
        initialize(cryptographicDomainParameters, CryptographicDomainParameters.class);
    }

    @Override
    public String name() {
        return "CryptographicDomainParameters";
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