package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeBenchmarkSubject extends KmipBenchmarkSubject<ExtensionType> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ExtensionTypeBenchmarkSubject() throws Exception {
    ExtensionType extensionType = ExtensionType
        .builder()
        .value(1)
        .build();
    initialize(extensionType, ExtensionType.class);
  }

  @Override
  public String name() {
    return "ExtensionType";
  }

}