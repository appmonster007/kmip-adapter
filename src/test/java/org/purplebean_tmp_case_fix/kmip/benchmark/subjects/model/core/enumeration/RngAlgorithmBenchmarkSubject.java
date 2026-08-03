package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<RngAlgorithm> {

  public RngAlgorithmBenchmarkSubject() throws Exception {
    RngAlgorithm rngAlgorithm = RngAlgorithm.Standard.UNSPECIFIED.inst();
    initialize(rngAlgorithm, RngAlgorithm.class);
  }

  @Override
  public String name() {
    return "RngAlgorithm";
  }

}
