package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeBenchmarkSubject extends KmipBenchmarkSubject<RngMode> {

  public RngModeBenchmarkSubject() throws Exception {
    RngMode rngMode = RngMode.Standard.UNSPECIFIED.inst();
    initialize(rngMode, RngMode.class);
  }

  @Override
  public String name() {
    return "RngMode";
  }

}
