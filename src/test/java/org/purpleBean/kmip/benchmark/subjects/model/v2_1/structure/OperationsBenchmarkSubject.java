package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Operations;

public class OperationsBenchmarkSubject extends KmipBenchmarkSubject<Operations> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public OperationsBenchmarkSubject() throws Exception {
    Operations subject = Operations.of(java.util.List.of());
    initialize(subject, Operations.class);
  }

  @Override
  public String name() {
    return "Operations";
  }
}