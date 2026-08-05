package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.RotateLatest;

/**
 * Benchmark subject for {@link RotateLatest}.
 */
public class RotateLatestBenchmarkSubject extends KmipBenchmarkSubject<RotateLatest> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RotateLatestBenchmarkSubject}.
   */
  public RotateLatestBenchmarkSubject() throws Exception {
    RotateLatest subject = RotateLatest.of(true);
    initialize(subject, RotateLatest.class);
  }

  @Override
  public String name() {
    return "RotateLatest";
  }
}