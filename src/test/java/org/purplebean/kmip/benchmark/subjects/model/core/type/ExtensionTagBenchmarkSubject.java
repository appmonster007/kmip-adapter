package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ExtensionTag;

/**
 * Benchmark subject for {@link ExtensionTag}.
 */
public class ExtensionTagBenchmarkSubject extends KmipBenchmarkSubject<ExtensionTag> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ExtensionTagBenchmarkSubject}.
   */
  public ExtensionTagBenchmarkSubject() throws Exception {
    ExtensionTag extensionTag = ExtensionTag
        .builder()
        .value(1)
        .build();
    initialize(extensionTag, ExtensionTag.class);
  }

  @Override
  public String name() {
    return "ExtensionTag";
  }

}