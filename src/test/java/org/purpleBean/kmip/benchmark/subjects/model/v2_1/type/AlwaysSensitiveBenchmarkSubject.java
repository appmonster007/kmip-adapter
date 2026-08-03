package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.AlwaysSensitive;

public class AlwaysSensitiveBenchmarkSubject extends KmipBenchmarkSubject<AlwaysSensitive> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public AlwaysSensitiveBenchmarkSubject() throws Exception {
    AlwaysSensitive subject = AlwaysSensitive.of(true);
    initialize(subject, AlwaysSensitive.class);
  }

  @Override
  public String name() {
    return "AlwaysSensitive";
  }
}