package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;

/**
 * Benchmark subject for {@link InteropFunction}.
 */
public class InteropFunctionBenchmarkSubject extends KmipBenchmarkSubject<InteropFunction> {

  /**
   * Constructs a new {@link InteropFunctionBenchmarkSubject}.
   */
  public InteropFunctionBenchmarkSubject() throws Exception {
    InteropFunction interopFunction = InteropFunction.Standard.BEGIN.inst();
    initialize(interopFunction, InteropFunction.class);
  }

  @Override
  public String name() {
    return "InteropFunction";
  }

}
