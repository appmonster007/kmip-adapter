package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityUri;

public class ValidationAuthorityUriBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationAuthorityUri> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationAuthorityUriBenchmarkSubject() throws Exception {
    ValidationAuthorityUri subject = ValidationAuthorityUri.of("default-string");
    initialize(subject, ValidationAuthorityUri.class);
  }

  @Override
  public String name() {
    return "ValidationAuthorityUri";
  }
}