package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ExtensionType;

/**
 * Benchmark subject for {@link ExtensionType}.
 */
public class ExtensionTypeBenchmarkSubject extends KmipBenchmarkSubject<ExtensionType> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ExtensionTypeBenchmarkSubject}.
   */
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