package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;

/**
 * Benchmark subject for {@link ValidationAuthorityType}.
 */
public class ValidationAuthorityTypeBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationAuthorityType> {

  /**
   * Constructs a new {@link ValidationAuthorityTypeBenchmarkSubject}.
   */
  public ValidationAuthorityTypeBenchmarkSubject() throws Exception {
    ValidationAuthorityType validationAuthorityType =
        ValidationAuthorityType.Standard.UNSPECIFIED.inst();
    initialize(validationAuthorityType, ValidationAuthorityType.class);
  }

  @Override
  public String name() {
    return "ValidationAuthorityType";
  }

}
