package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorBenchmarkSubject extends KmipBenchmarkSubject<ValidityIndicator> {

  public ValidityIndicatorBenchmarkSubject() throws Exception {
    ValidityIndicator validityIndicator = ValidityIndicator.Standard.VALID.inst();
    initialize(validityIndicator, ValidityIndicator.class);
  }

  @Override
  public String name() {
    return "ValidityIndicator";
  }

}
