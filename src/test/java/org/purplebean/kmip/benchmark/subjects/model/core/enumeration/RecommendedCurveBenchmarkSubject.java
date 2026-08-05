package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;

/**
 * Benchmark subject for {@link RecommendedCurve}.
 */
public class RecommendedCurveBenchmarkSubject extends KmipBenchmarkSubject<RecommendedCurve> {

  /**
   * Constructs a new {@link RecommendedCurveBenchmarkSubject}.
   */
  public RecommendedCurveBenchmarkSubject() throws Exception {
    RecommendedCurve recommendedCurve = RecommendedCurve.Standard.P_192.inst();
    initialize(recommendedCurve, RecommendedCurve.class);
  }

  @Override
  public String name() {
    return "RecommendedCurve";
  }

}
