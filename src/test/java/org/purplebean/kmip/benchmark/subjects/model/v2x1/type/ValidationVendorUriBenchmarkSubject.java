package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationVendorUri;

/**
 * Benchmark subject for {@link ValidationVendorUri}.
 */
public class ValidationVendorUriBenchmarkSubject extends KmipBenchmarkSubject<ValidationVendorUri> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ValidationVendorUriBenchmarkSubject}.
   */
  public ValidationVendorUriBenchmarkSubject() throws Exception {
    ValidationVendorUri subject = ValidationVendorUri.of("default-string");
    initialize(subject, ValidationVendorUri.class);
  }

  @Override
  public String name() {
    return "ValidationVendorUri";
  }
}