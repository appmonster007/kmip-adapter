package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationProfile;

public class ValidationProfileBenchmarkSubject extends KmipBenchmarkSubject<ValidationProfile> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationProfileBenchmarkSubject() throws Exception {
    ValidationProfile subject = ValidationProfile.of("default-string");
    initialize(subject, ValidationProfile.class);
  }

  @Override
  public String name() {
    return "ValidationProfile";
  }
}