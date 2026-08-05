package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;

/**
 * Benchmark subject for {@link ValidityIndicator}.
 */
public class ValidityIndicatorBenchmarkSubject extends KmipBenchmarkSubject<ValidityIndicator> {

  /**
   * Constructs a new {@link ValidityIndicatorBenchmarkSubject}.
   */
  public ValidityIndicatorBenchmarkSubject() throws Exception {
    ValidityIndicator validityIndicator = ValidityIndicator.Standard.VALID.inst();
    initialize(validityIndicator, ValidityIndicator.class);
  }

  @Override
  public String name() {
    return "ValidityIndicator";
  }

}
