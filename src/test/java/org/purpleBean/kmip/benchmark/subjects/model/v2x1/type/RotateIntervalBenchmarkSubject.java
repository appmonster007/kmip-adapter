package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.RotateInterval;

public class RotateIntervalBenchmarkSubject extends KmipBenchmarkSubject<RotateInterval> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RotateIntervalBenchmarkSubject() throws Exception {
    RotateInterval subject = RotateInterval.of(12345L);
    initialize(subject, RotateInterval.class);
  }

  @Override
  public String name() {
    return "RotateInterval";
  }
}