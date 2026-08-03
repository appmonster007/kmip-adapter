package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;

public class ValidationVersionMajorBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationVersionMajor> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationVersionMajorBenchmarkSubject() throws Exception {
    ValidationVersionMajor subject = ValidationVersionMajor.of(123);
    initialize(subject, ValidationVersionMajor.class);
  }

  @Override
  public String name() {
    return "ValidationVersionMajor";
  }
}