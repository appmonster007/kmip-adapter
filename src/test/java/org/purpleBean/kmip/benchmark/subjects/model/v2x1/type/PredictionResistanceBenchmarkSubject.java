package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.PredictionResistance;

public class PredictionResistanceBenchmarkSubject
    extends KmipBenchmarkSubject<PredictionResistance> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PredictionResistanceBenchmarkSubject() throws Exception {
    PredictionResistance subject = PredictionResistance.of(true);
    initialize(subject, PredictionResistance.class);
  }

  @Override
  public String name() {
    return "PredictionResistance";
  }
}