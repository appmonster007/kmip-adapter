package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Constraint;

/**
 * Benchmark subject for {@link Constraint}.
 */
public class ConstraintBenchmarkSubject extends KmipBenchmarkSubject<Constraint> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link ConstraintBenchmarkSubject}.
   */
  public ConstraintBenchmarkSubject() throws Exception {
    Constraint subject = Constraint.of(Collections.emptyList());
    initialize(subject, Constraint.class);
  }

  @Override
  public String name() {
    return "Constraint";
  }
}
