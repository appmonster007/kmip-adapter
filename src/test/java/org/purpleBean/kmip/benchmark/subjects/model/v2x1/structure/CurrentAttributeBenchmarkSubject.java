package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.CurrentAttribute;

public class CurrentAttributeBenchmarkSubject extends KmipBenchmarkSubject<CurrentAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public CurrentAttributeBenchmarkSubject() throws Exception {
    CurrentAttribute subject = CurrentAttribute
        .builder()
        .attribute(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, CurrentAttribute.class);
  }

  @Override
  public String name() {
    return "CurrentAttribute";
  }
}