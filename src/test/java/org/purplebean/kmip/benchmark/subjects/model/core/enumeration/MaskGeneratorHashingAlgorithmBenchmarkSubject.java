package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.MaskGeneratorHashingAlgorithm;

/**
 * Benchmark subject for {@link MaskGeneratorHashingAlgorithm}.
 */
public class MaskGeneratorHashingAlgorithmBenchmarkSubject
    extends KmipBenchmarkSubject<MaskGeneratorHashingAlgorithm> {

  /**
   * Constructs a new {@link MaskGeneratorHashingAlgorithmBenchmarkSubject}.
   */
  public MaskGeneratorHashingAlgorithmBenchmarkSubject() throws Exception {
    MaskGeneratorHashingAlgorithm maskGeneratorHashingAlgorithm =
        MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD2);
    initialize(maskGeneratorHashingAlgorithm, MaskGeneratorHashingAlgorithm.class);
  }

  @Override
  public String name() {
    return "MaskGeneratorHashingAlgorithm";
  }

}
