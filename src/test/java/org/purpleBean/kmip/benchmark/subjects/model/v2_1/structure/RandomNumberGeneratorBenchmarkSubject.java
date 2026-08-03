package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.v2_1.structure.RandomNumberGenerator;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

public class RandomNumberGeneratorBenchmarkSubject
    extends KmipBenchmarkSubject<RandomNumberGenerator> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

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