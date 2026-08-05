package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;

/**
 * Benchmark subject for {@link Fips186Variation}.
 */
public class Fips186VariationBenchmarkSubject extends KmipBenchmarkSubject<Fips186Variation> {

  /**
   * Constructs a new {@link Fips186VariationBenchmarkSubject}.
   */
  public Fips186VariationBenchmarkSubject() throws Exception {
    Fips186Variation fips186Variation = Fips186Variation.Standard.UNSPECIFIED.inst();
    initialize(fips186Variation, Fips186Variation.class);
  }

  @Override
  public String name() {
    return "Fips186Variation";
  }

}
