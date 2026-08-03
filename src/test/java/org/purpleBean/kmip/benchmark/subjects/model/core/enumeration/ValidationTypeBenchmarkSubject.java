package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeBenchmarkSubject extends KmipBenchmarkSubject<ValidationType> {

  public ValidationTypeBenchmarkSubject() throws Exception {
    ValidationType validationType = ValidationType.Standard.UNSPECIFIED.inst();
    initialize(validationType, ValidationType.class);
  }

  @Override
  public String name() {
    return "ValidationType";
  }

}
