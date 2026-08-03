package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityCountry;

public class ValidationAuthorityCountryBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationAuthorityCountry> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationAuthorityCountryBenchmarkSubject() throws Exception {
    ValidationAuthorityCountry subject = ValidationAuthorityCountry.of("default-string");
    initialize(subject, ValidationAuthorityCountry.class);
  }

  @Override
  public String name() {
    return "ValidationAuthorityCountry";
  }
}