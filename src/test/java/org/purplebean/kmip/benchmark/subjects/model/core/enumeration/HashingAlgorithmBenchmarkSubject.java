package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;

/**
 * Benchmark subject for {@link HashingAlgorithm}.
 */
public class HashingAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<HashingAlgorithm> {

  /**
   * Constructs a new {@link HashingAlgorithmBenchmarkSubject}.
   */
  public HashingAlgorithmBenchmarkSubject() throws Exception {
    HashingAlgorithm hashingAlgorithm = HashingAlgorithm.Standard.MD2.inst();
    initialize(hashingAlgorithm, HashingAlgorithm.class);
  }

  @Override
  public String name() {
    return "HashingAlgorithm";
  }

}
