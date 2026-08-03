package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Objects;

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