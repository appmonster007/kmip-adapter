package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;

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