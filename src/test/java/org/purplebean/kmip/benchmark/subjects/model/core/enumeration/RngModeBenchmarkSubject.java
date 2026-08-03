package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RngMode;

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
