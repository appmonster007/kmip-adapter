package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationBenchmarkSubject extends KmipBenchmarkSubject<Fips186Variation> {

  public Fips186VariationBenchmarkSubject() throws Exception {
    Fips186Variation fips186Variation = Fips186Variation.Standard.UNSPECIFIED.inst();
    initialize(fips186Variation, Fips186Variation.class);
  }

  @Override
  public String name() {
    return "Fips186Variation";
  }

}
