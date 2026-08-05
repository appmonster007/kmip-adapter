package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ShreddingAlgorithm;

/**
 * Benchmark subject for {@link ShreddingAlgorithm}.
 */
public class ShreddingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<ShreddingAlgorithm> {

  /**
   * Constructs a new {@link ShreddingAlgorithmBenchmarkSubject}.
   */
  public ShreddingAlgorithmBenchmarkSubject() throws Exception {
    ShreddingAlgorithm shreddingAlgorithm = ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
    initialize(shreddingAlgorithm, ShreddingAlgorithm.class);
  }

  @Override
  public String name() {
    return "ShreddingAlgorithm";
  }

}
