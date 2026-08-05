package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;

/**
 * Benchmark subject for {@link PaddingMethod}.
 */
public class PaddingMethodBenchmarkSubject extends KmipBenchmarkSubject<PaddingMethod> {

  /**
   * Constructs a new {@link PaddingMethodBenchmarkSubject}.
   */
  public PaddingMethodBenchmarkSubject() throws Exception {
    PaddingMethod paddingMethod = PaddingMethod.Standard.NONE.inst();
    initialize(paddingMethod, PaddingMethod.class);
  }

  @Override
  public String name() {
    return "PaddingMethod";
  }

}
