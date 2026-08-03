package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.Rights;

public class RightsBenchmarkSubject extends KmipBenchmarkSubject<Rights> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public RightsBenchmarkSubject() throws Exception {
    Rights subject = Rights.of(java.util.List.of());
    initialize(subject, Rights.class);
  }

  @Override
  public String name() {
    return "Rights";
  }
}