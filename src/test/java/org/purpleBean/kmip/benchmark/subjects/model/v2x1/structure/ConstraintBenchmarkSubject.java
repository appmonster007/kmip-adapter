package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import java.util.Collections;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.Constraint;

public class ConstraintBenchmarkSubject extends KmipBenchmarkSubject<Constraint> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ConstraintBenchmarkSubject() throws Exception {
    Constraint subject = Constraint.of(Collections.emptyList());
    initialize(subject, Constraint.class);
  }

  @Override
  public String name() {
    return "Constraint";
  }
}
