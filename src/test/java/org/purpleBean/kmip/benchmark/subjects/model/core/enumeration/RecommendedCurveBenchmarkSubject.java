package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveBenchmarkSubject extends KmipBenchmarkSubject<RecommendedCurve> {

    public RecommendedCurveBenchmarkSubject() throws Exception {
        RecommendedCurve recommendedCurve = RecommendedCurve.Standard.P_192.inst();
        initialize(recommendedCurve, RecommendedCurve.class);
    }

    @Override
    public String name() {
        return "RecommendedCurve";
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
