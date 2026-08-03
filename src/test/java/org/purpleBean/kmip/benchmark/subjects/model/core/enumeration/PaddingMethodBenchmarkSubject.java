package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;

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
