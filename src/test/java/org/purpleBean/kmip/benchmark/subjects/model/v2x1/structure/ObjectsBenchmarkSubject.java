package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.Objects;

public class ObjectsBenchmarkSubject extends KmipBenchmarkSubject<Objects> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ObjectsBenchmarkSubject() throws Exception {
    Objects subject = Objects.of(java.util.List.of());
    initialize(subject, Objects.class);
  }

  @Override
  public String name() {
    return "Objects";
  }
}