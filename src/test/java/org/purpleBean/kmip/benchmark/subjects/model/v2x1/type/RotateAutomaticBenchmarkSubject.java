package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.RotateAutomatic;

public class RotateAutomaticBenchmarkSubject extends KmipBenchmarkSubject<RotateAutomatic> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RotateAutomaticBenchmarkSubject() throws Exception {
    RotateAutomatic subject = RotateAutomatic.of(true);
    initialize(subject, RotateAutomatic.class);
  }

  @Override
  public String name() {
    return "RotateAutomatic";
  }
}