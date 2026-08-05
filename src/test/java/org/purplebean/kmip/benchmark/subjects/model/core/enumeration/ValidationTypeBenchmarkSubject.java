package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidationType;

/**
 * Benchmark subject for {@link ValidationType}.
 */
public class ValidationTypeBenchmarkSubject extends KmipBenchmarkSubject<ValidationType> {

  /**
   * Constructs a new {@link ValidationTypeBenchmarkSubject}.
   */
  public ValidationTypeBenchmarkSubject() throws Exception {
    ValidationType validationType = ValidationType.Standard.UNSPECIFIED.inst();
    initialize(validationType, ValidationType.class);
  }

  @Override
  public String name() {
    return "ValidationType";
  }

}
