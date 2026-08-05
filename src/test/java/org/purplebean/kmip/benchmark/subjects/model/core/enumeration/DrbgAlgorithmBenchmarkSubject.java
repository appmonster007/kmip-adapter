package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.DrbgAlgorithm;

/**
 * Benchmark subject for {@link DrbgAlgorithm}.
 */
public class DrbgAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<DrbgAlgorithm> {

  /**
   * Constructs a new {@link DrbgAlgorithmBenchmarkSubject}.
   */
  public DrbgAlgorithmBenchmarkSubject() throws Exception {
    DrbgAlgorithm drbgAlgorithm = DrbgAlgorithm.Standard.UNSPECIFIED.inst();
    initialize(drbgAlgorithm, DrbgAlgorithm.class);
  }

  @Override
  public String name() {
    return "DrbgAlgorithm";
  }

}
