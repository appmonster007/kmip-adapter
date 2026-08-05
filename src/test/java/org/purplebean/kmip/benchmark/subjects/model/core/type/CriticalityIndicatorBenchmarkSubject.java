package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;

/**
 * Benchmark subject for {@link CriticalityIndicator}.
 */
public class CriticalityIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<CriticalityIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CriticalityIndicatorBenchmarkSubject}.
   */
  public CriticalityIndicatorBenchmarkSubject() throws Exception {
    CriticalityIndicator criticalityIndicator = CriticalityIndicator
        .builder()
        .value(true)
        .build();
    initialize(criticalityIndicator, CriticalityIndicator.class);
  }

  @Override
  public String name() {
    return "CriticalityIndicator";
  }

}