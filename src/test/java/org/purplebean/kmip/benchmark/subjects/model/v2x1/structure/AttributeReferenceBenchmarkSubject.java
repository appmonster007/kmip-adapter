package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;

/**
 * Benchmark subject for {@link AttributeReference}.
 */
public class AttributeReferenceBenchmarkSubject extends KmipBenchmarkSubject<AttributeReference> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AttributeReferenceBenchmarkSubject}.
   */
  public AttributeReferenceBenchmarkSubject() throws Exception {
    AttributeReference subject = AttributeReference
        .builder()
        .build();
    initialize(subject, AttributeReference.class);
  }

  @Override
  public String name() {
    return "AttributeReference";
  }
}