package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityUri;

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