package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<ShreddingAlgorithm> {

  public ShreddingAlgorithmBenchmarkSubject() throws Exception {
    ShreddingAlgorithm shreddingAlgorithm = ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
    initialize(shreddingAlgorithm, ShreddingAlgorithm.class);
  }

  @Override
  public String name() {
    return "ShreddingAlgorithm";
  }

}
