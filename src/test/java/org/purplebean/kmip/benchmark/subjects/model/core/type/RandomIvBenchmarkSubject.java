package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.RandomIv;

/**
 * Benchmark subject for {@link RandomIv}.
 */
public class RandomIvBenchmarkSubject extends KmipBenchmarkSubject<RandomIv> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RandomIvBenchmarkSubject}.
   */
  public RandomIvBenchmarkSubject() throws Exception {
    RandomIv randomIv = RandomIv.of(true);
    initialize(randomIv, RandomIv.class);
  }

  @Override
  public String name() {
    return "RandomIv";
  }

}