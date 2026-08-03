package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;

public class UsageLimitsCountBenchmarkSubject extends KmipBenchmarkSubject<UsageLimitsCount> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public UsageLimitsCountBenchmarkSubject() throws Exception {
    UsageLimitsCount usageLimitsCount = UsageLimitsCount
        .builder()
        .value(100L)
        .build();
    initialize(usageLimitsCount, UsageLimitsCount.class);
  }

  @Override
  public String name() {
    return "UsageLimitsCount";
  }

}