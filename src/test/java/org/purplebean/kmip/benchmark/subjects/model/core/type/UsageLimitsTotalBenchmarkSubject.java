package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;

/**
 * Benchmark subject for {@link UsageLimitsTotal}.
 */
public class UsageLimitsTotalBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsTotal> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link UsageLimitsTotalBenchmarkSubject}.
   */
  public UsageLimitsTotalBenchmarkSubject() throws Exception {
    UsageLimitsTotal usageLimitsTotal = UsageLimitsTotal
        .builder()
        .value(1000L)
        .build();
    initialize(usageLimitsTotal, UsageLimitsTotal.class);
  }

  @Override
  public String name() {
    return "UsageLimitsTotal";
  }

}