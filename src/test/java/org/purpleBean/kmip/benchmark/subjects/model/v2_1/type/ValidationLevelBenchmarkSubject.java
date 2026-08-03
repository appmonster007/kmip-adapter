package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationLevel;

public class ValidationLevelBenchmarkSubject extends KmipBenchmarkSubject<ValidationLevel> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationLevelBenchmarkSubject() throws Exception {
    ValidationLevel subject = ValidationLevel.of(123);
    initialize(subject, ValidationLevel.class);
  }

  @Override
  public String name() {
    return "ValidationLevel";
  }
}