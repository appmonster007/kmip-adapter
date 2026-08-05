package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;

/**
 * Benchmark subject for {@link InitIndicator}.
 */
public class InitIndicatorBenchmarkSubject extends KmipBenchmarkSubject<InitIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link InitIndicatorBenchmarkSubject}.
   */
  public InitIndicatorBenchmarkSubject() throws Exception {
    InitIndicator subject = InitIndicator.of(true);
    initialize(subject, InitIndicator.class);
  }

  @Override
  public String name() {
    return "InitIndicator";
  }
}