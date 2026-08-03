package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Sensitive;

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