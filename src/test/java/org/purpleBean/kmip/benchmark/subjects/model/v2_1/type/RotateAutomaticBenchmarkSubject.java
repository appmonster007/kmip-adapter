package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;

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