package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

public class InitIndicatorBenchmarkSubject extends KmipBenchmarkSubject<InitIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public InitIndicatorBenchmarkSubject() throws Exception {
    InitIndicator subject = InitIndicator.of(true);
    initialize(subject, InitIndicator.class);
  }

  @Override
  public String name() {
    return "InitIndicator";
  }
}