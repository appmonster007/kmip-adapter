package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityCountry;

/**
 * Benchmark subject for {@link ValidationAuthorityCountry}.
 */
public class ValidationAuthorityCountryBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationAuthorityCountry> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ValidationAuthorityCountryBenchmarkSubject}.
   */
  public ValidationAuthorityCountryBenchmarkSubject() throws Exception {
    ValidationAuthorityCountry subject = ValidationAuthorityCountry.of("default-string");
    initialize(subject, ValidationAuthorityCountry.class);
  }

  @Override
  public String name() {
    return "ValidationAuthorityCountry";
  }
}