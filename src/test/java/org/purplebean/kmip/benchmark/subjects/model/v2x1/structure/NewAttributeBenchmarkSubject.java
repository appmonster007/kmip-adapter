package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;

/**
 * Benchmark subject for {@link NewAttribute}.
 */
public class NewAttributeBenchmarkSubject extends KmipBenchmarkSubject<NewAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link NewAttributeBenchmarkSubject}.
   */
  public NewAttributeBenchmarkSubject() throws Exception {
    NewAttribute subject = NewAttribute
        .builder()
        .attribute(org.purplebean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, NewAttribute.class);
  }

  @Override
  public String name() {
    return "NewAttribute";
  }
}