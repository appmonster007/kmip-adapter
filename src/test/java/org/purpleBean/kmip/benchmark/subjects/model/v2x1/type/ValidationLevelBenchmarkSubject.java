package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;

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