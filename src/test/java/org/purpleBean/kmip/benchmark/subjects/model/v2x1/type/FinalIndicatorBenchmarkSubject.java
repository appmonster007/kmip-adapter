package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;

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