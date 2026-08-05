package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Right;

/**
 * Benchmark subject for {@link Right}.
 */
public class RightBenchmarkSubject extends KmipBenchmarkSubject<Right> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RightBenchmarkSubject}.
   */
  public RightBenchmarkSubject() throws Exception {
    Right subject = Right
        .builder()
        .build();
    initialize(subject, Right.class);
  }

  @Override
  public String name() {
    return "Right";
  }
}