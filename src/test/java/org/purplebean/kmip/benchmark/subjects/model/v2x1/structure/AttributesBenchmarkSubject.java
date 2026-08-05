package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Attributes;

/**
 * Benchmark subject for {@link Attributes}.
 */
public class AttributesBenchmarkSubject extends KmipBenchmarkSubject<Attributes> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link AttributesBenchmarkSubject}.
   */
  public AttributesBenchmarkSubject() throws Exception {
    Attributes subject = Attributes.of(Collections.emptyList());
    initialize(subject, Attributes.class);
  }

  @Override
  public String name() {
    return "Attributes";
  }
}
