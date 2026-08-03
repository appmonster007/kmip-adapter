package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;

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