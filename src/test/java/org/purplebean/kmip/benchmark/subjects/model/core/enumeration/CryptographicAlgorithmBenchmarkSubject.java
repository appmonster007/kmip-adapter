package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;

/**
 * Benchmark subject for {@link CryptographicAlgorithm}.
 */
public class CryptographicAlgorithmBenchmarkSubject
    extends KmipBenchmarkSubject<CryptographicAlgorithm> {

  /**
   * Constructs a new {@link CryptographicAlgorithmBenchmarkSubject}.
   */
  public CryptographicAlgorithmBenchmarkSubject() throws Exception {
    CryptographicAlgorithm cryptographicAlgorithm = CryptographicAlgorithm.Standard.DES.inst();
    initialize(cryptographicAlgorithm, CryptographicAlgorithm.class);
  }

  @Override
  public String name() {
    return "CryptographicAlgorithm";
  }

}
