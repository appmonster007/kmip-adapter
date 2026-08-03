package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LeaseTime;

public class LeaseTimeBenchmarkSubject extends KmipBenchmarkSubject<LeaseTime> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public LeaseTimeBenchmarkSubject() throws Exception {
    var fixed = 100;
    LeaseTime leaseTime = LeaseTime
        .builder()
        .value(fixed)
        .build();
    initialize(leaseTime, LeaseTime.class);
  }

  @Override
  public String name() {
    return "LeaseTime";
  }

}
