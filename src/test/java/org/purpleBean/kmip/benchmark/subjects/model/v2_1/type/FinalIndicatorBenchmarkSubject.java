package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;

public class FinalIndicatorBenchmarkSubject extends KmipBenchmarkSubject<FinalIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public FinalIndicatorBenchmarkSubject() throws Exception {
    FinalIndicator subject = FinalIndicator.of(true);
    initialize(subject, FinalIndicator.class);
  }

  @Override
  public String name() {
    return "FinalIndicator";
  }
}