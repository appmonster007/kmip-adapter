package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

public class RecommendedCurveBenchmarkSubject extends KmipBenchmarkSubject<RecommendedCurve> {

    public RecommendedCurveBenchmarkSubject() throws Exception {
        RecommendedCurve recommendedCurve = new RecommendedCurve(RecommendedCurve.Standard.P_192);
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
