package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;

/**
 * Benchmark subject for {@link ValidationProfile}.
 */
public class ValidationProfileBenchmarkSubject extends KmipBenchmarkSubject<ValidationProfile> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ValidationProfileBenchmarkSubject}.
   */
  public ValidationProfileBenchmarkSubject() throws Exception {
    ValidationProfile subject = ValidationProfile.of("default-string");
    initialize(subject, ValidationProfile.class);
  }

  @Override
  public String name() {
    return "ValidationProfile";
  }
}