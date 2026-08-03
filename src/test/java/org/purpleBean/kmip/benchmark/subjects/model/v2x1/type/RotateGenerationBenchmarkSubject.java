package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.RotateGeneration;

public class RotateGenerationBenchmarkSubject extends KmipBenchmarkSubject<RotateGeneration> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RotateGenerationBenchmarkSubject() throws Exception {
    RotateGeneration subject = RotateGeneration.of(123);
    initialize(subject, RotateGeneration.class);
  }

  @Override
  public String name() {
    return "RotateGeneration";
  }
}