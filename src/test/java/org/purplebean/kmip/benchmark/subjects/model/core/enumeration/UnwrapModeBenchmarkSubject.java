package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.UnwrapMode;

/**
 * Benchmark subject for {@link UnwrapMode}.
 */
public class UnwrapModeBenchmarkSubject extends KmipBenchmarkSubject<UnwrapMode> {

  /**
   * Constructs a new {@link UnwrapModeBenchmarkSubject}.
   */
  public UnwrapModeBenchmarkSubject() throws Exception {
    UnwrapMode unwrapMode = UnwrapMode.Standard.UNSPECIFIED.inst();
    initialize(unwrapMode, UnwrapMode.class);
  }

  @Override
  public String name() {
    return "UnwrapMode";
  }

}
