package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;

public class NewAttributeBenchmarkSubject extends KmipBenchmarkSubject<NewAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public NewAttributeBenchmarkSubject() throws Exception {
    NewAttribute subject = NewAttribute
        .builder()
        .attribute(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, NewAttribute.class);
  }

  @Override
  public String name() {
    return "NewAttribute";
  }
}