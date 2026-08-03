package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

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
