package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionNameBenchmarkSubject extends KmipBenchmarkSubject<ExtensionName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ExtensionNameBenchmarkSubject() throws Exception {
    ExtensionName extensionName = ExtensionName
        .builder()
        .value("test-extension")
        .build();
    initialize(extensionName, ExtensionName.class);
  }

  @Override
  public String name() {
    return "ExtensionName";
  }

}