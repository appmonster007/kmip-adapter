package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;

public class RecommendedCurveBenchmarkSubject extends KmipBenchmarkSubject<RecommendedCurve> {

  public RecommendedCurveBenchmarkSubject() throws Exception {
    RecommendedCurve recommendedCurve = RecommendedCurve.Standard.P_192.inst();
    initialize(recommendedCurve, RecommendedCurve.class);
  }

  @Override
  public String name() {
    return "RecommendedCurve";
  }

}
