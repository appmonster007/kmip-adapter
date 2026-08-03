package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Qlength;

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