package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ExtensionName;

/**
 * Benchmark subject for {@link ExtensionName}.
 */
public class ExtensionNameBenchmarkSubject extends KmipBenchmarkSubject<ExtensionName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ExtensionNameBenchmarkSubject}.
   */
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