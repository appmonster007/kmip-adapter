package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;

/**
 * Benchmark subject for {@link SplitKeyMethod}.
 */
public class SplitKeyMethodBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyMethod> {

  /**
   * Constructs a new {@link SplitKeyMethodBenchmarkSubject}.
   */
  public SplitKeyMethodBenchmarkSubject() throws Exception {
    SplitKeyMethod splitKeyMethod = SplitKeyMethod.Standard.XOR.inst();
    initialize(splitKeyMethod, SplitKeyMethod.class);
  }

  @Override
  public String name() {
    return "SplitKeyMethod";
  }

}
