package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<CriticalityIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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