package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Qlength;

public class QlengthBenchmarkSubject extends KmipBenchmarkSubject<Qlength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public QlengthBenchmarkSubject() throws Exception {
    Qlength qlength = Qlength
        .builder()
        .value(128)
        .build();
    initialize(qlength, Qlength.class);
  }

  @Override
  public String name() {
    return "Qlength";
  }

}