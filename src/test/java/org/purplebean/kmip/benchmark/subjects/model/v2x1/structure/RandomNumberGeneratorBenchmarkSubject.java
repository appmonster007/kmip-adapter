package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.RandomNumberGenerator;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;

/**
 * Benchmark subject for {@link RandomNumberGenerator}.
 */
public class RandomNumberGeneratorBenchmarkSubject
    extends KmipBenchmarkSubject<RandomNumberGenerator> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RandomNumberGeneratorBenchmarkSubject}.
   */
  public RandomNumberGeneratorBenchmarkSubject() throws Exception {
    RandomNumberGenerator subject =
        RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
    initialize(subject, RandomNumberGenerator.class);
  }

  @Override
  public String name() {
    return "RandomNumberGenerator";
  }
}