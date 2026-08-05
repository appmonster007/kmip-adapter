package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.RotateOffset;

/**
 * Benchmark subject for {@link RotateOffset}.
 */
public class RotateOffsetBenchmarkSubject extends KmipBenchmarkSubject<RotateOffset> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RotateOffsetBenchmarkSubject}.
   */
  public RotateOffsetBenchmarkSubject() throws Exception {
    RotateOffset subject = RotateOffset.of(12345L);
    initialize(subject, RotateOffset.class);
  }

  @Override
  public String name() {
    return "RotateOffset";
  }
}