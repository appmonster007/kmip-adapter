package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodBenchmarkSubject extends KmipBenchmarkSubject<PaddingMethod> {

  public PaddingMethodBenchmarkSubject() throws Exception {
    PaddingMethod paddingMethod = PaddingMethod.Standard.NONE.inst();
    initialize(paddingMethod, PaddingMethod.class);
  }

  @Override
  public String name() {
    return "PaddingMethod";
  }

}
