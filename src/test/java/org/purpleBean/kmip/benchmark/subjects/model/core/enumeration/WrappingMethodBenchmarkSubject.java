package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;

public class WrappingMethodBenchmarkSubject extends KmipBenchmarkSubject<WrappingMethod> {

  public WrappingMethodBenchmarkSubject() throws Exception {
    WrappingMethod wrappingMethod = WrappingMethod.Standard.ENCRYPT.inst();
    initialize(wrappingMethod, WrappingMethod.class);
  }

  @Override
  public String name() {
    return "WrappingMethod";
  }

}
