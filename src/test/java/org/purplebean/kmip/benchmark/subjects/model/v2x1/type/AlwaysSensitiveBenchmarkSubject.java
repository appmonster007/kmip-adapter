package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.AlwaysSensitive;

/**
 * Benchmark subject for {@link AlwaysSensitive}.
 */
public class AlwaysSensitiveBenchmarkSubject extends KmipBenchmarkSubject<AlwaysSensitive> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link AlwaysSensitiveBenchmarkSubject}.
   */
  public AlwaysSensitiveBenchmarkSubject() throws Exception {
    AlwaysSensitive subject = AlwaysSensitive.of(true);
    initialize(subject, AlwaysSensitive.class);
  }

  @Override
  public String name() {
    return "AlwaysSensitive";
  }
}