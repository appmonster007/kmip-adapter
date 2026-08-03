package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.Sensitive;

public class SensitiveBenchmarkSubject extends KmipBenchmarkSubject<Sensitive> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SensitiveBenchmarkSubject() throws Exception {
    Sensitive subject = Sensitive.of(true);
    initialize(subject, Sensitive.class);
  }

  @Override
  public String name() {
    return "Sensitive";
  }
}