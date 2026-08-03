package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Fresh;

public class FreshBenchmarkSubject extends KmipBenchmarkSubject<Fresh> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public FreshBenchmarkSubject() throws Exception {
    Fresh fresh = Fresh
        .builder()
        .value(true)
        .build();
    initialize(fresh, Fresh.class);
  }

  @Override
  public String name() {
    return "Fresh";
  }

}