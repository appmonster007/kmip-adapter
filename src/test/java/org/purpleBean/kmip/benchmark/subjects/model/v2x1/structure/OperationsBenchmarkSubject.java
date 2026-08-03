package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Operations;

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