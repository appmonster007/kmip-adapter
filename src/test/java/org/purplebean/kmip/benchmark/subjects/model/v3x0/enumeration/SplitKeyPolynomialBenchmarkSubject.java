package org.purplebean.kmip.benchmark.subjects.model.v3x0.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.SplitKeyPolynomial;

/**
 * Benchmark subject for {@link SplitKeyPolynomial}.
 */
public class SplitKeyPolynomialBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyPolynomial> {

  /**
   * Constructs a new {@link SplitKeyPolynomialBenchmarkSubject}.
   */
  public SplitKeyPolynomialBenchmarkSubject() throws Exception {
    SplitKeyPolynomial splitKeyPolynomial = SplitKeyPolynomial.Standard.POLYNOMIAL_283.inst();
    initialize(splitKeyPolynomial, SplitKeyPolynomial.class);
  }

  @Override
  public String name() {
    return "SplitKeyPolynomial";
  }

}
