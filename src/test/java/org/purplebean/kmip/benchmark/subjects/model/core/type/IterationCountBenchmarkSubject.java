package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.IterationCount;

/**
 * Benchmark subject for {@link IterationCount}.
 */
public class IterationCountBenchmarkSubject extends KmipBenchmarkSubject<IterationCount> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link IterationCountBenchmarkSubject}.
   */
  public IterationCountBenchmarkSubject() throws Exception {
    IterationCount iterationCount = IterationCount
        .builder()
        .value(1000)
        .build();
    initialize(iterationCount, IterationCount.class);
  }

  @Override
  public String name() {
    return "IterationCount";
  }

}