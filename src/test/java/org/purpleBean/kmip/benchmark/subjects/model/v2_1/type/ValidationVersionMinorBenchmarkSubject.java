package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMinor;

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