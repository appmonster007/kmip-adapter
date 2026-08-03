package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.ValidationVendorUri;

public class ValidationVendorUriBenchmarkSubject extends KmipBenchmarkSubject<ValidationVendorUri> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationVendorUriBenchmarkSubject() throws Exception {
    ValidationVendorUri subject = ValidationVendorUri.of("default-string");
    initialize(subject, ValidationVendorUri.class);
  }

  @Override
  public String name() {
    return "ValidationVendorUri";
  }
}