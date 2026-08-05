package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.RotateInterval;

/**
 * Benchmark subject for {@link RotateInterval}.
 */
public class RotateIntervalBenchmarkSubject extends KmipBenchmarkSubject<RotateInterval> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RotateIntervalBenchmarkSubject}.
   */
  public RotateIntervalBenchmarkSubject() throws Exception {
    RotateInterval subject = RotateInterval.of(12345L);
    initialize(subject, RotateInterval.class);
  }

  @Override
  public String name() {
    return "RotateInterval";
  }
}