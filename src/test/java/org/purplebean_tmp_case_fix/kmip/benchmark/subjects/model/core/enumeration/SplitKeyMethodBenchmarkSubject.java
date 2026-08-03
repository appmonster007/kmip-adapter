package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;

public class SplitKeyMethodBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyMethod> {

  public SplitKeyMethodBenchmarkSubject() throws Exception {
    SplitKeyMethod splitKeyMethod = SplitKeyMethod.Standard.XOR.inst();
    initialize(splitKeyMethod, SplitKeyMethod.class);
  }

  @Override
  public String name() {
    return "SplitKeyMethod";
  }

}
