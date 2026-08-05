package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.CurrentAttribute;

/**
 * Benchmark subject for {@link CurrentAttribute}.
 */
public class CurrentAttributeBenchmarkSubject extends KmipBenchmarkSubject<CurrentAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link CurrentAttributeBenchmarkSubject}.
   */
  public CurrentAttributeBenchmarkSubject() throws Exception {
    CurrentAttribute subject = CurrentAttribute
        .builder()
        .attribute(org.purplebean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, CurrentAttribute.class);
  }

  @Override
  public String name() {
    return "CurrentAttribute";
  }
}