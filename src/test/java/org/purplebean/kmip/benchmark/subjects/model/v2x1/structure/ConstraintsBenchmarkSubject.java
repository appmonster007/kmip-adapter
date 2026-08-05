package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Constraints;

/**
 * Benchmark subject for {@link Constraints}.
 */
public class ConstraintsBenchmarkSubject extends KmipBenchmarkSubject<Constraints> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link ConstraintsBenchmarkSubject}.
   */
  public ConstraintsBenchmarkSubject() throws Exception {
    Constraints subject = Constraints.of(Collections.emptyList());
    initialize(subject, Constraints.class);
  }

  @Override
  public String name() {
    return "Constraints";
  }
}
