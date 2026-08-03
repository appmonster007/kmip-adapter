package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ProtectionPeriod;

public class ProtectionPeriodBenchmarkSubject extends KmipBenchmarkSubject<ProtectionPeriod> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ProtectionPeriodBenchmarkSubject() throws Exception {
    ProtectionPeriod subject = ProtectionPeriod.of(12345L);
    initialize(subject, ProtectionPeriod.class);
  }

  @Override
  public String name() {
    return "ProtectionPeriod";
  }
}