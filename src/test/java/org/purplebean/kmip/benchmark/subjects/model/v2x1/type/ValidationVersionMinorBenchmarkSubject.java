package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMinor;

public class ValidationVersionMinorBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationVersionMinor> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationVersionMinorBenchmarkSubject() throws Exception {
    ValidationVersionMinor subject = ValidationVersionMinor.of(123);
    initialize(subject, ValidationVersionMinor.class);
  }

  @Override
  public String name() {
    return "ValidationVersionMinor";
  }
}