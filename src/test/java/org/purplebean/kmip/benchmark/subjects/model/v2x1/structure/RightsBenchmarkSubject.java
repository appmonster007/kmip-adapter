package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Rights;

/**
 * Benchmark subject for {@link Rights}.
 */
public class RightsBenchmarkSubject extends KmipBenchmarkSubject<Rights> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RightsBenchmarkSubject}.
   */
  public RightsBenchmarkSubject() throws Exception {
    Rights subject = Rights.of(java.util.List.of());
    initialize(subject, Rights.class);
  }

  @Override
  public String name() {
    return "Rights";
  }
}